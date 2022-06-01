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
                   transaction_category_id
                )
                VALUES(
                    :id,
                    :description,
                    :date,
                    :value,
                    :extraDescription,
                    :accountId,
                    :transactionTypeId,
                    :transactionCategoryId
                )
            """;

    String UPDATE_TRANSACTION = """
                UPDATE cx_account_transaction SET
                description = :description,
                date = :description,
                value = :description,
                extra_description = :description,
                account_id = :accountId,
                transaction_type_id = :transactionTypeId,
                transaction_category_id = :transactionCategoryId
                updated_at = :dateUpdated
                WHERE account_id = :WhereAccountId AND id = :WhereTransactionId
            """;

    String DELETE_TRANSACTION_SOFT_DELETE = """
                UPDATE cx_account_transaction SET
                deleted_at = :dateDeleted
                WHERE account_id = :WhereAccountId AND id = :WhereTransactionId
            """;

    String FIND_ALL_TRANSACTION_BY_ACCOUNT = """
                SELECT t.id,
                       t.description,
                       t.date,
                       t.extra_description,
                       ctt.description as type,
                       tc.description as category,
                       t.created_at,
                       t.updated_at
                    from cx_account_transaction t
                    join cx_accounts ca on t.account_id = ca.id
                    join cx_transaction_type ctt on t.transaction_type_id = ctt.id
                    join cx_transaction_category tc on t.transaction_category_id = tc.id
                    where t.account_id::text = ::accountId
            """;

    String FIND_BY_TRANSACTION_ID = FIND_ALL_TRANSACTION_BY_ACCOUNT+" AND t.id=:transactionId";

}
