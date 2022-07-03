package com.financial.api.infra.repositories.account;

public interface Queries {
    String INSERT_ACCOUNT = """
                INSERT INTO cx_accounts(id, description, initial_amount, type, created_at, updated_at) 
                VALUES(:id,:description,:initialAmount,:type, :createdAt, :updatedAt)
            """;

    String INSERT_ACCOUNT_USER = """
                INSERT INTO cx_user_accounts(user_id,account_id,owner) VALUES(:userId,:accountId,:accountOwner)          
            """;

    String UPDATE_ACCOUNT = """
                UPDATE cx_accounts SET description= :description, initial_amount= :initialAmount,
                type=:type, updated_at=:dateUpdated WHERE id::text = :whereId
            """;

    String DELETE_ACCOUNT_SOFTDELETE = """
                UPDATE cx_accounts SET deleted_at= :date WHERE id::text = :whereId
            """;

    String FIND_ALL_ACCOUNTS_BY_USER = """
                 SELECT a.id, a.description, a.initial_amount, a.type, a.created_at, a.updated_at, cua.owner
                        FROM CX_ACCOUNTS a
                        JOIN cx_user_accounts cua on a.id = cua.account_id
                        JOIN cx_user cxu on cua.user_id = cxu.id
                        WHERE cxu.id::text = :userId AND a.deleted_at is null
            """;

    String GET_COUNT_ACCOUNTS_BY_USER = """
                 SELECT COUNT(a.id) as total
                        FROM CX_ACCOUNTS a
                        JOIN cx_user_accounts cua on a.id = cua.account_id
                        JOIN cx_user cxu on cua.user_id = cxu.id
                        WHERE cxu.id::text = :userId AND a.deleted_at is null
          
            """;

    String FIND_BY_ACCOUNT_ID = FIND_ALL_ACCOUNTS_BY_USER + "AND a.id::text=:accountId";

}
