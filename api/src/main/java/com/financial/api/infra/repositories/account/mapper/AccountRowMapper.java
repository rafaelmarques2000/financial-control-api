package com.financial.api.infra.repositories.account.mapper;

import com.financial.api.domain.account.enums.AccountType;
import com.financial.api.domain.account.model.Account;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;

import java.time.LocalDateTime;
import java.util.function.BiFunction;

public class AccountRowMapper {

    public static BiFunction<Row, RowMetadata, Account> toAccount() {
        return (row, rowMetadata) -> new Account(
                row.get("id", String.class),
                row.get("description", String.class),
                row.get("initial_amount", Integer.class),
                row.get("type", AccountType.class),
                row.get("created_at", LocalDateTime.class),
                row.get("updated_at", LocalDateTime.class),
                row.get("owner", Boolean.class)
        );
    }

}
