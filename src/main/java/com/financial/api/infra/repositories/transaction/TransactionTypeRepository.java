package com.financial.api.infra.repositories.transaction;

import com.financial.api.domain.transaction.model.TransactionType;
import com.financial.api.domain.transaction.repository.ITransactionTypeRepository;
import com.financial.api.infra.repositories.AbstractRepository;
import com.financial.api.infra.repositories.transaction.mapper.NoReactiveTransactionTypeRowMapper;
import com.financial.api.infra.repositories.transaction.mapper.TransactionTypeRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;

import java.util.Optional;

public class TransactionTypeRepository extends AbstractRepository implements ITransactionTypeRepository {

    private JdbcTemplate jdbcTemplate;

    public TransactionTypeRepository(DatabaseClient databaseClient, TransactionalOperator operator, JdbcTemplate jdbcTemplate) {
        super(databaseClient, operator);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Flux<TransactionType> findAll() {
        return databaseClient.sql(Queries.FIND_ALL_TRANSACTION_TYPE).map(TransactionTypeRowMapper.toTransactionType())
                .all();
    }

    @Override
    public Optional<TransactionType> noReactiveFindBySlugname(String slugname) {
         return jdbcTemplate
                 .query(Queries.FIND_BY_SLUGNAME.replace(":slugname", "'"+slugname+"'"), new NoReactiveTransactionTypeRowMapper())
                 .stream().findFirst();
    }
}
