package com.financial.api.infra.repositories.transaction;

import com.financial.api.domain.transaction.model.Transaction;
import com.financial.api.domain.transaction.repository.ITransactionRepository;
import com.financial.api.infra.repositories.AbstractRepository;
import com.financial.api.infra.repositories.transaction.Queries;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;

public class TransactionRepository extends AbstractRepository implements ITransactionRepository {

    public TransactionRepository(DatabaseClient databaseClient, TransactionalOperator operator) {
        super(databaseClient, operator);
    }

    @Override
    public Mono<Transaction> save(Transaction transaction) {
        return databaseClient.sql(Queries.CREATE_TRANSACTION)
                .bind("id", transaction.id())
                .bind("description", transaction.description())
                .bind("date", transaction.date())
                .bind("extraDescription", transaction.extraDescription())
                .bind("accountId", transaction.accountId())
                .bind("transactionTypeId", transaction.transactionTypeId())
                .bind("transactionCategoryId", transaction.transactionCategoryId())
                .then().thenReturn(transaction);
    }

    @Override
    public Mono<Transaction> update(Transaction transaction) {
        return databaseClient.sql(Queries.UPDATE_TRANSACTION)
                .bind("description", transaction.description())
                .bind("date", transaction.date())
                .bind("extraDescription", transaction.extraDescription())
                .bind("accountId", transaction.accountId())
                .bind("transactionTypeId", transaction.transactionTypeId())
                .bind("transactionCategoryId", transaction.transactionCategoryId())
                .bind("dateUpdated", LocalDateTime.now())
                .bind("WhereAccountId", transaction.accountId())
                .bind("WhereTransactionId", transaction.id())
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
        return null;
    }

    @Override
    public Mono<Transaction> findById(String accountId, String transactionId) {
        return null;
    }
}
