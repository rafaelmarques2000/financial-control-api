package com.financial.api.infra.repositories.transaction;

import com.financial.api.domain.transaction.model.TransactionType;
import com.financial.api.domain.transaction.repository.ITransactionTypeRepository;
import com.financial.api.infra.repositories.AbstractRepository;
import com.financial.api.infra.repositories.transaction.mapper.TransactionTypeRowMapper;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;

public class TransactionTypeRepository extends AbstractRepository implements ITransactionTypeRepository {

    public TransactionTypeRepository(DatabaseClient databaseClient, TransactionalOperator operator) {
        super(databaseClient, operator);
    }

    @Override
    public Flux<TransactionType> findAll() {
        return databaseClient.sql(Queries.FIND_ALL_TRANSACTION_TYPE).map(TransactionTypeRowMapper.toTransactionType())
                .all();
    }
}
