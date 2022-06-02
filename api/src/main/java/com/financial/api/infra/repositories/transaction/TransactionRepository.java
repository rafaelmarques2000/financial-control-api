package com.financial.api.infra.repositories.transaction;

import com.financial.api.domain.transaction.model.Transaction;
import com.financial.api.domain.transaction.repository.ITransactionRepository;
import com.financial.api.infra.repositories.AbstractRepository;
import com.financial.api.infra.repositories.transaction.mapper.TransactionRowMapper;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
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
                .bind("transactionTypeId", UUID.fromString(transaction.Type().id()))
                .bind("transactionCategoryId", UUID.fromString(transaction.Category().id()))
                .bind("createdAt", transaction.created_at())
                .bind("updatedAt", transaction.updated_at())
                .then().thenReturn(transaction);
    }

    @Override
    public Mono<Transaction> update(Transaction transaction) {
        return databaseClient.sql(Queries.UPDATE_TRANSACTION)
                .bind("description", transaction.description())
                .bind("date", transaction.date())
                .bind("value", transaction.value())
                .bind("extraDescription", transaction.extraDescription())
                .bind("transactionTypeId", UUID.fromString(transaction.Type().id()))
                .bind("transactionCategoryId", UUID.fromString(transaction.Category().id()))
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
    public Flux<Transaction> findAll(String accountId) {
        return databaseClient.sql(Queries.FIND_ALL_TRANSACTION_BY_ACCOUNT)
                .bind("accountId", accountId)
                .map(TransactionRowMapper.toTransaction())
                .all();
    }

    @Override
    public Mono<Transaction> findById(String accountId, String transactionId) {
        return databaseClient.sql(Queries.FIND_BY_TRANSACTION_ID)
                .bind("accountId", accountId)
                .bind("transactionId", transactionId)
                .map(TransactionRowMapper.toTransaction())
                .one();
    }
}
