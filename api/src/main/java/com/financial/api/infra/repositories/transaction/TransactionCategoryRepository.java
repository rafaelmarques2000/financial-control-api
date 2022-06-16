package com.financial.api.infra.repositories.transaction;

import com.financial.api.domain.transaction.model.TransactionCategory;
import com.financial.api.domain.transaction.model.TransactionType;
import com.financial.api.domain.transaction.repository.ITransactionCategoryRepository;
import com.financial.api.infra.repositories.AbstractRepository;
import com.financial.api.infra.repositories.transaction.mapper.NoReactiveTransactionCategoryRowMapper;
import com.financial.api.infra.repositories.transaction.mapper.TransactionCategoryRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

public class TransactionCategoryRepository extends AbstractRepository implements ITransactionCategoryRepository {

    private JdbcTemplate jdbcTemplate;

    public TransactionCategoryRepository(DatabaseClient databaseClient, TransactionalOperator operator, JdbcTemplate jdbcTemplate) {
        super(databaseClient, operator);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Flux<TransactionCategory> findAll(String transactionTypeId) {
        return databaseClient.sql(Queries.FIND_ALL_TRANSACTION_TYPE_CATEGORIES)
                .bind("transactionTypeId", UUID.fromString(transactionTypeId))
                .map(TransactionCategoryRowMapper.toTransactionCategory()).all();
    }

    @Override
    public Optional<TransactionCategory> noReactiveFindBySlugname(String slugname) {
        return jdbcTemplate.query(Queries.FIND_CATEGORY_BY_SLUGNAME.replace(":slugname", "'"+slugname+"'"), new NoReactiveTransactionCategoryRowMapper())
                .stream().findFirst();
    }

}
