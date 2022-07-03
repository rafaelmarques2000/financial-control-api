package com.financial.api.infra.repositories;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractRepository {

    protected DatabaseClient databaseClient;
    protected TransactionalOperator operator;

    public AbstractRepository(DatabaseClient databaseClient, TransactionalOperator operator) {
        this.databaseClient = databaseClient;
        this.operator = operator;
    }

    public Mono<Integer> getCountTotal(String query, HashMap<String, String> params, String countColumnParameter) {
       DatabaseClient.GenericExecuteSpec client =  databaseClient.sql(query);
       for(Map.Entry<String, String> entry : params.entrySet()) {
           client = client.bind(entry.getKey(), entry.getValue());
       }
       return client.map((row, rowMetadata) -> row.get(countColumnParameter, Integer.class)).one();
    }

    public Double calculateTotalPages(Integer total, Integer size) {
         return Math.ceil((double) total / (double) size);
    }

    public Integer calculateOffSetPagination(Integer size, Integer page) {
         return (size * page) - size;
    }
}
