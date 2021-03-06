package com.financial.api.infra.repositories.user.mapper;

import com.financial.api.domain.user.enums.UserStatus;
import com.financial.api.domain.user.model.User;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.function.BiFunction;

public class UserRowMapper {

    public static BiFunction<Row, RowMetadata, User> toUser() {
        return (row, rowMetadata) -> new User(
                row.get("id", String.class),
                row.get("login", String.class),
                row.get("password", String.class),
                row.get("view_name", String.class),
                row.get("created_at", LocalDateTime.class),
                row.get("updated_at", LocalDateTime.class),
                row.get("status", UserStatus.class)
        );
    }

}
