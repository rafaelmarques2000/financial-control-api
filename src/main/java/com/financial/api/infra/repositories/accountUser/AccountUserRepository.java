package com.financial.api.infra.repositories.accountUser;
import com.financial.api.domain.accountUser.model.AccountShareUser;
import com.financial.api.domain.accountUser.repository.IAccountUserRepository;
import com.financial.api.infra.repositories.AbstractRepository;
import com.financial.api.infra.repositories.accountUser.Queries;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


public class AccountUserRepository extends AbstractRepository implements IAccountUserRepository {

    public AccountUserRepository(DatabaseClient databaseClient, TransactionalOperator operator) {
        super(databaseClient, operator);
    }

    @Override
    public Mono<String> shareAccount(String userShareId, String accountId) {
        return databaseClient.sql(Queries.INSERT_ACCOUNT_USER)
                .bind("userId", UUID.fromString(userShareId))
                .bind("accountId", UUID.fromString(accountId))
                .bind("accountOwner", false)
                .then()
                .as(operator::transactional)
                .thenReturn(userShareId);
    }

    @Override
    public Flux<AccountShareUser> listAllSharing(String userId, String accountId) {
        return databaseClient.sql(Queries.LIST_ALL_ACCOUNT_SHARE_USERS)
                .bind("userId", userId)
                .bind("accountId", accountId)
                .map((row, rowMetadata) -> new AccountShareUser(
                        row.get("accountId", String.class),
                        row.get("userId", String.class),
                        row.get("viewName", String.class)
                )).all();
    }

    @Override
    public Mono<Void> deleteSharing(String userSharingId, String accountId) {
        return databaseClient.sql(Queries.DELETE_SHARING)
                .bind("userSharingId", userSharingId)
                .bind("accountId", accountId)
                .then();
    }
}
