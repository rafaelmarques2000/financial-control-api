package com.financial.api.infra.repositories.service;

public interface Queries {

    String INSERT_SERVICE = """
                INSERT INTO cx_service
                (id,
                description,
                "value",
                recurrence_type,
                user_id,
                account_id,
                created_at,
                updated_at,
                status)
                values (
                    :id,
                    :description,
                    :value,
                    :recurrenceType,
                    :userId,
                    :accountId,
                    :createdAt,
                    :updatedAt,
                    :status
                )
            """;


    String UPDATE_SERVICE = """
                 UPDATE cx_service SET
                 description = :description,
                 "value" = :value,
                  recurrence_type = :recurrenceType,
                  user_id = :userId,
                  account_id = :accountId,
                  updated_at = :updatedAt,
                  status = :status
                  WHERE user_id = :userId AND id = :serviceId
            """;

    String SOFT_DELETE_SERVICE = """
                  UPDATE cx_service SET
                  deleted_at = :deletedAt
                  WHERE user_id = :userId AND id = :serviceId
            """;


    String FIND_ALL_SERVICES = """
                SELECT
                id,
                description,
                "value",
                recurrence_type,
                user_id,
                account_id,
                created_at,
                updated_at,
                status
                FROM cx_service WHERE deleted_at is null AND status = 'ACTIVE'
            """;

    String FIND_ALL_SERVICES_BY_USER = """
                SELECT
                id,
                description,
                "value",
                recurrence_type,
                user_id,
                account_id,
                created_at,
                updated_at,
                status
                FROM cx_service WHERE user_id = :userId AND deleted_at is null
            """;


    String FIND_SERVICE_BY_ID = FIND_ALL_SERVICES_BY_USER + " AND id = :serviceId";

}
