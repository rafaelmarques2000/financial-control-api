package com.financial.api.infra.repositories.transaction.mapper;

import com.financial.api.domain.account.enums.AccountType;
import com.financial.api.domain.account.model.Account;
import com.financial.api.domain.transaction.model.Transaction;
import com.financial.api.domain.transaction.model.TransactionCategory;
import com.financial.api.domain.transaction.model.TransactionType;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.BiFunction;

public class TransactionRowMapper {
    public static BiFunction<Row, RowMetadata, Transaction> toTransaction() {
        return (row, rowMetadata) -> new Transaction(
                row.get("id", String.class),
                row.get("description", String.class),
                row.get("date", LocalDate.class),
                row.get("value", Integer.class),
                row.get("extra_description", String.class),
                row.get("account_id", String.class),
                new TransactionType(row.get("type_id",String.class),row.get("type", String.class)),
                new TransactionCategory(row.get("category_id", String.class), row.get("category", String.class)),
                row.get("created_at", LocalDateTime.class),
                row.get("updated_at", LocalDateTime.class)
        );
    }
}
