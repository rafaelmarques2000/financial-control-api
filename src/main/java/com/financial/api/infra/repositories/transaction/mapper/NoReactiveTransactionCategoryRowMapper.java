package com.financial.api.infra.repositories.transaction.mapper;

import com.financial.api.domain.transaction.model.TransactionCategory;
import com.financial.api.domain.transaction.model.TransactionType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class NoReactiveTransactionCategoryRowMapper implements RowMapper<TransactionCategory> {
    @Override
    public TransactionCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TransactionCategory(
                rs.getObject("id", UUID.class).toString(),
                rs.getObject("description", String.class)
        );
    }
}
