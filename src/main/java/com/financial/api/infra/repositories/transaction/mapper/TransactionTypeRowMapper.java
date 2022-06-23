package com.financial.api.infra.repositories.transaction.mapper;

import com.financial.api.domain.transaction.model.Transaction;
import com.financial.api.domain.transaction.model.TransactionCategory;
import com.financial.api.domain.transaction.model.TransactionType;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.BiFunction;

public class TransactionTypeRowMapper {
    public static BiFunction<Row, RowMetadata, TransactionType> toTransactionType() {
        return (row, rowMetadata) -> new TransactionType(
                row.get("id", String.class),
                row.get("description", String.class)
        );
    }
}
