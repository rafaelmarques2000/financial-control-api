package com.financial.api.infra.repositories.transaction;

public interface Queries {
    String CREATE_TRANSACTION = """
                INSERT INTO cx_account_transaction(
                   id,
                   description,
                   date,
                   value,
                   extra_description,
                   account_id,
                   transaction_type_id,
                   transaction_category_id,
                   created_at,
                   updated_at
                )
                VALUES(
                    :id,
                    :description,
                    :date,
                    :value,
                    :extraDescription,
                    :accountId,
                    :transactionTypeId,
                    :transactionCategoryId,
                    :createdAt,
                    :updatedAt
                )
            """;

    String UPDATE_TRANSACTION = """
                UPDATE cx_account_transaction SET
                description = :description,
                date = :date,
                value = :value,
                extra_description = :extraDescription,
                transaction_type_id = :transactionTypeId,
                transaction_category_id = :transactionCategoryId,
                updated_at = :dateUpdated
                WHERE account_id = :WhereAccountId AND id = :WhereTransactionId
            """;

    String DELETE_TRANSACTION_SOFT_DELETE = """
                UPDATE cx_account_transaction SET
                deleted_at = :dateDeleted
                WHERE account_id::text = :WhereAccountId AND id::text = :WhereTransactionId
            """;

    String FIND_ALL_TRANSACTION_BY_ACCOUNT = """
                SELECT t.id,
                       t.description,
                       t.date,
                       t.value,
                       t.extra_description,
                       t.account_id,
                       ctt.id as type_id,
                       ctt.description as type,
                       tc.id as category_id,
                       tc.description as category,
                       t.created_at,
                       t.updated_at
                    from cx_account_transaction t
                    join cx_accounts ca on t.account_id = ca.id
                    join cx_transaction_type ctt on t.transaction_type_id = ctt.id
                    join cx_transaction_category tc on t.transaction_category_id = tc.id
                    where t.account_id::text = :accountId AND t.deleted_at is null
            """;

    String FIND_BY_TRANSACTION_ID = FIND_ALL_TRANSACTION_BY_ACCOUNT+" AND t.id::text=:transactionId";



    String FIND_ALL_TRANSACTION_TYPE = """
                 SELECT id, description FROM cx_transaction_type
            """;

    String FIND_ALL_TRANSACTION_TYPE_CATEGORIES = """
                 SELECT id, description FROM cx_transaction_category WHERE transaction_type_id = :categoryId
            """;





















}
