package com.financial.api.infra.repositories.transaction.mapper;

import com.financial.api.domain.transaction.model.TransactionCategory;
import com.financial.api.domain.transaction.model.TransactionType;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;

import java.util.function.BiFunction;

public class TransactionCategoryRowMapper {
    public static BiFunction<Row, RowMetadata, TransactionCategory> toTransactionCategory() {
        return (row, rowMetadata) -> new TransactionCategory(
                row.get("id", String.class),
                row.get("description", String.class)
        );
    }
}
