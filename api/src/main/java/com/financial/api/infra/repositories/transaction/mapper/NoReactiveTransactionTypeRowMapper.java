package com.financial.api.infra.repositories.transaction.mapper;

import com.financial.api.domain.transaction.model.TransactionType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class NoReactiveTransactionTypeRowMapper implements RowMapper<TransactionType> {
    @Override
    public TransactionType mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TransactionType(
                rs.getObject("id", UUID.class).toString(),
                rs.getObject("description", String.class)
        );
    }
}
