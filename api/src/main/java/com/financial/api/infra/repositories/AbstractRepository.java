package com.financial.api.infra.repositories;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.transaction.reactive.TransactionalOperator;

public abstract class AbstractRepository {

    protected DatabaseClient databaseClient;
    protected TransactionalOperator operator;

    public AbstractRepository(DatabaseClient databaseClient, TransactionalOperator operator) {
        this.databaseClient = databaseClient;
        this.operator = operator;
    }
}
