package com.financial.api.infra.repositories.transaction.repository;

import com.financial.api.domain.transaction.model.Transaction;
import com.financial.api.domain.transaction.repository.ITransactionRepository;
import com.financial.api.infra.repositories.AbstractRepository;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TransactionRepository extends AbstractRepository implements ITransactionRepository {

    public TransactionRepository(DatabaseClient databaseClient, TransactionalOperator operator) {
        super(databaseClient, operator);
    }

    @Override
    public Mono<Transaction> save(Transaction transaction) {
        return null;
    }

    @Override
    public Mono<Transaction> update(Transaction transaction) {
        return null;
    }

    @Override
    public Mono<Void> delete(String transactionId) {
        return null;
    }

    @Override
    public Flux<Transaction> findAll() {
        return null;
    }

    @Override
    public Mono<Transaction> findById(String transactionId) {
        return null;
    }
}
