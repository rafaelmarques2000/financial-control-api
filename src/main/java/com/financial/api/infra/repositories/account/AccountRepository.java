package com.financial.api.infra.repositories.account;
import com.financial.api.domain.account.filter.AccountFilter;
import com.financial.api.domain.account.model.Account;
import com.financial.api.domain.account.repository.IAccountRepository;
import com.financial.api.domain.global.interfaces.IFilter;
import com.financial.api.infra.repositories.AbstractRepository;
import com.financial.api.infra.repositories.account.Queries;
import com.financial.api.infra.repositories.account.mapper.AccountRowMapper;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;


public class AccountRepository extends AbstractRepository implements IAccountRepository {

    public AccountRepository(DatabaseClient databaseClient, TransactionalOperator operator) {
        super(databaseClient, operator);
    }

    @Override
    public Mono<Account> save(String userId, Account account) {
        return databaseClient.sql(Queries.INSERT_ACCOUNT)
                .bind("id", UUID.fromString(account.id()))
                .bind("description", account.description())
                .bind("initialAmount", account.initialAmount())
                .bind("type", account.type())
                .bind("createdAt", account.createdAt())
                .bind("updatedAt", account.updatedAt())
                .fetch().rowsUpdated()
                .then(databaseClient
                        .sql(Queries.INSERT_ACCOUNT_USER)
                        .bind("userId", UUID.fromString(userId))
                        .bind("accountId", UUID.fromString(account.id()))
                        .bind("accountOwner", account.owner())
                        .fetch().rowsUpdated())
                .as(operator::transactional)
                .thenReturn(account);
    }

    @Override
    public Mono<Account> update(String userId, Account account) {
        return databaseClient.sql(Queries.UPDATE_ACCOUNT)
                .bind("description", account.description())
                .bind("initialAmount", account.initialAmount())
                .bind("type", account.type())
                .bind("dateUpdated", LocalDateTime.now())
                .bind("whereId", account.id())
                .then().thenReturn(account);
    }

    @Override
    public Mono<Void> delete(String userId, String accountId) {
        return databaseClient.sql(Queries.DELETE_ACCOUNT_SOFTDELETE)
                .bind("date", LocalDateTime.now())
                .bind("whereId", accountId)
                .then();
    }

    @Override
    public Flux<Account> findAll(String userId, AccountFilter accountFilter) {

        if(!accountFilter.hasFilter()) {
            return databaseClient.sql(Queries.FIND_ALL_ACCOUNTS_BY_USER)
                    .bind("userId", userId)
                    .map(AccountRowMapper.toAccount())
                    .all();
        }

        String sql = Queries.FIND_ALL_ACCOUNTS_BY_USER;

        if(accountFilter.getDescription() != null) {
            sql+="AND a.description LIKE :description";
        }

        return databaseClient.sql(sql)
                .bind("description", "%"+accountFilter.getDescription()+"%")
                .bind("userId", userId)
                .map(AccountRowMapper.toAccount())
                .all();
    }

    @Override
    public Mono<Account> findById(String userId, String accountId) {
        return databaseClient.sql(Queries.FIND_BY_ACCOUNT_ID)
                .bind("userId", userId)
                .bind("accountId", accountId)
                .map(AccountRowMapper.toAccount()).one();
    }
}
