package com.financial.api.infra.domain.accountUser;

public interface Queries {
    String INSERT_ACCOUNT_USER = """
                INSERT INTO cx_user_accounts(user_id,account_id,owner) VALUES(:userId,:accountId,:accountOwner)          
            """;

    String LIST_ALL_ACCOUNT_SHARE_USERS = """
                SELECT a.id as accountId, cxu.id as userId ,cxu.view_name as viewName
                FROM CX_ACCOUNTS a
                         JOIN cx_user_accounts cua on a.id = cua.account_id
                         JOIN cx_user cxu on cua.user_id = cxu.id
                WHERE cua.user_id::text <> :userId
                AND a.id::text = :accountId
            """;

}
