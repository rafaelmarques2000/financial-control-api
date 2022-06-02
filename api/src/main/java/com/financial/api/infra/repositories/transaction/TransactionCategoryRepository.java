package com.financial.api.infra.repositories.transaction;

import com.financial.api.domain.transaction.model.TransactionCategory;
import com.financial.api.domain.transaction.model.TransactionType;
import com.financial.api.domain.transaction.repository.ITransactionCategoryRepository;
import com.financial.api.infra.repositories.AbstractRepository;
import com.financial.api.infra.repositories.transaction.mapper.TransactionCategoryRowMapper;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;

import java.util.UUID;

public class TransactionCategoryRepository extends AbstractRepository implements ITransactionCategoryRepository {

    public TransactionCategoryRepository(DatabaseClient databaseClient, TransactionalOperator operator) {
        super(databaseClient, operator);
    }

    @Override
    public Flux<TransactionCategory> findAll(String transactionTypeId) {
        return databaseClient.sql(Queries.FIND_ALL_TRANSACTION_TYPE_CATEGORIES)
                .bind("categoryId", UUID.fromString(transactionTypeId))
                .map(TransactionCategoryRowMapper.toTransactionCategory()).all();
    }
}
