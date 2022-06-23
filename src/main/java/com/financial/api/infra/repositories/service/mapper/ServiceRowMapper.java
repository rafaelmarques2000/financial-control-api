package com.financial.api.infra.repositories.service.mapper;

import com.financial.api.domain.services.enums.RecurrenceType;
import com.financial.api.domain.services.enums.Status;
import com.financial.api.domain.services.model.Service;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;

import java.time.LocalDateTime;
import java.util.function.BiFunction;

public class ServiceRowMapper {


    public static BiFunction<Row, RowMetadata, Service> toRowFromService() {
        return (row, rowMetadata) ->
            new Service(
                    row.get("id", String.class),
                    row.get("description", String.class),
                    row.get("value", Integer.class),
                    row.get("recurrence_type", RecurrenceType.class),
                    row.get("user_id", String.class),
                    row.get("account_id", String.class),
                    row.get("created_at", LocalDateTime.class),
                    row.get("updated_at", LocalDateTime.class),
                    row.get("status", Status.class)
            );
    }

}
