package com.financial.api.infra.repositories.transaction;

import com.financial.api.domain.transaction.filter.TransactionFilter;
import com.financial.api.domain.transaction.model.Transaction;
import com.financial.api.domain.transaction.repository.ITransactionRepository;
import com.financial.api.infra.repositories.AbstractRepository;
import com.financial.api.infra.repositories.transaction.mapper.TransactionRowMapper;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.Parameter;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionRepository extends AbstractRepository implements ITransactionRepository {

    public TransactionRepository(DatabaseClient databaseClient, TransactionalOperator operator) {
        super(databaseClient, operator);
    }

    @Override
    public Mono<Transaction> save(Transaction transaction) {
        return databaseClient.sql(Queries.CREATE_TRANSACTION)
                .bind("id", UUID.fromString(transaction.id()))
                .bind("description", transaction.description())
                .bind("date", transaction.date())
                .bind("value", transaction.value())
                .bind("extraDescription", transaction.extraDescription())
                .bind("accountId", UUID.fromString(transaction.accountId()))
                .bind("transactionTypeId", UUID.fromString(transaction.type().id()))
                .bind("transactionCategoryId", UUID.fromString(transaction.category().id()))
                .bind("createdAt", transaction.created_at())
                .bind("updatedAt", transaction.updated_at())
                .bind("serviceReference", Parameter.fromOrEmpty(transaction.serviceReference(), UUID.class))
                .then().thenReturn(transaction);
    }

    @Override
    public Mono<Transaction> update(Transaction transaction) {
        return databaseClient.sql(Queries.UPDATE_TRANSACTION)
                .bind("description", transaction.description())
                .bind("date", transaction.date())
                .bind("value", transaction.value())
                .bind("extraDescription", transaction.extraDescription())
                .bind("transactionTypeId", UUID.fromString(transaction.type().id()))
                .bind("transactionCategoryId", UUID.fromString(transaction.category().id()))
                .bind("dateUpdated", LocalDateTime.now())
                .bind("WhereAccountId", UUID.fromString(transaction.accountId()))
                .bind("WhereTransactionId", UUID.fromString(transaction.id()))
                .then().thenReturn(transaction);
    }

    @Override
    public Mono<Void> delete(String accountId, String transactionId) {
        return databaseClient.sql(Queries.DELETE_TRANSACTION_SOFT_DELETE)
                .bind("dateDeleted", LocalDateTime.now())
                .bind("WhereAccountId", accountId)
                .bind("WhereTransactionId", transactionId)
                .then();
    }

    @Override
    public Flux<Transaction> findAll(String userId, String accountId, TransactionFilter filter) {

        if(!filter.hasFilter()) {
            if(accountId != null) {
                return databaseClient.sql(Queries.FIND_ALL_TRANSACTION_BY_ACCOUNT)
                        .bind("accountId", accountId)
                        .bind("userId", userId)
                        .map(TransactionRowMapper.toTransaction())
                        .all();
            }

            return databaseClient.sql(Queries.FIND_ALL_TRANSACTION_BY_USER_ACCOUNTS)
                    .bind("userId", userId)
                    .map(TransactionRowMapper.toTransaction())
                    .all();
        }

        String sql = accountId != null ? Queries.FIND_ALL_TRANSACTION_BY_ACCOUNT : Queries.FIND_ALL_TRANSACTION_BY_USER_ACCOUNTS;

        if(filter.getBeginDate() != null) {
            sql = sql.concat(" AND t.date BETWEEN :beginDate AND :endDate");
        }

        if(filter.getCategoryId() != null) {
            sql = sql.concat(" AND tc.id::text = :categoryId");
        }

        if(filter.getTypeId() != null) {
            sql = sql.concat(" AND ctt.id::text = :typeId");
        }

        return getAllTransactionByFilter(userId, accountId, filter, sql);
    }

    @Override
    public Mono<Transaction> findById(String accountId, String transactionId) {
        return databaseClient.sql(Queries.FIND_BY_TRANSACTION_ID)
                .bind("accountId", accountId)
                .bind("transactionId", transactionId)
                .map(TransactionRowMapper.toTransaction())
                .one();
    }

    @Override
    public Flux<Transaction> findByDateAndServiceReference(LocalDate beginDate, LocalDate endDate, String serviceReference) {
        return databaseClient.sql(Queries.FIND_ALL_TRANSACTION_BY_SERVICE_REFERENCE)
                .bind("serviceReference", UUID.fromString(serviceReference))
                .bind("beginDate", beginDate)
                .bind("endDate", endDate)
                .map(TransactionRowMapper.toTransaction())
                .all();
    }

    private Flux<Transaction> getAllTransactionByFilter(String userId, String accountId, TransactionFilter filter, String sql) {

        DatabaseClient.GenericExecuteSpec sqlClient = databaseClient.sql(sql)
                .bind("userId", userId);

        if(accountId != null) {
             sqlClient = sqlClient.bind("accountId", accountId);
        }

        if(filter.getBeginDate() != null) {
            sqlClient = sqlClient
                    .bind("beginDate", filter.getBeginDate())
                    .bind("endDate", filter.getEndDate());
        }

        if(filter.getCategoryId() != null) {
            sqlClient = sqlClient.bind("categoryId", filter.getCategoryId());
        }

        if(filter.getTypeId() != null) {
            sqlClient = sqlClient.bind("typeId", filter.getTypeId());
        }

        return sqlClient.map(TransactionRowMapper.toTransaction()).all();
    }
}
