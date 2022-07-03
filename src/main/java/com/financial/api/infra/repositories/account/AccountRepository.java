package com.financial.api.infra.repositories.account;
import com.financial.api.domain.account.filter.AccountFilter;
import com.financial.api.domain.account.model.Account;
import com.financial.api.domain.account.model.AccountPaginationResult;
import com.financial.api.domain.account.repository.IAccountRepository;
import com.financial.api.infra.repositories.AbstractRepository;
import com.financial.api.infra.repositories.account.mapper.AccountRowMapper;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
import java.util.HashMap;
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
    public Mono<AccountPaginationResult> findAll(String userId, AccountFilter accountFilter) {

        AccountPaginationResult paginatedAccount = new AccountPaginationResult();

        HashMap<String,String> params = new HashMap<>();
        params.put("userId", userId);

        if(!accountFilter.hasFilter()) {
            return getCountTotal(Queries.GET_COUNT_ACCOUNTS_BY_USER, params, "total")
                    .map(total -> {
                        var sql = Queries.FIND_ALL_ACCOUNTS_BY_USER + " LIMIT :limit OFFSET :offset";
                        paginatedAccount.setTotalPage(calculateTotalPages(total, accountFilter.size()));
                        paginatedAccount.setPage(accountFilter.page());
                        return databaseClient.sql(sql)
                        .bind("userId", userId)
                        .bind("limit", accountFilter.size())
                        .bind("offset", calculateOffSetPagination(accountFilter.size(), accountFilter.page()))
                        .map(AccountRowMapper.toAccount())
                        .all().collectList();
                    })
                    .publishOn(Schedulers.boundedElastic()).mapNotNull(listAccounts -> {
                        paginatedAccount.setItems(listAccounts.block());
                        return null;
                    })
                    .thenReturn(paginatedAccount);
        }

        String sql = Queries.FIND_ALL_ACCOUNTS_BY_USER;
        String sqlCount = Queries.GET_COUNT_ACCOUNTS_BY_USER;

        if(accountFilter.description() != null) {
            sqlCount += " AND a.description LIKE :description";
            sql+=" AND a.description LIKE :description";
        }

        sql+= " LIMIT :limit OFFSET :offset";

        DatabaseClient.GenericExecuteSpec client = databaseClient.sql(sql);

        if(accountFilter.description() != null) {
            params.put("description", "%"+accountFilter.description()+"%");
            client = client.bind("description", "%"+accountFilter.description()+"%");
        }

        DatabaseClient.GenericExecuteSpec finalClient = client;

        return getCountTotal(sqlCount, params, "total")
                .map(total -> {
                    paginatedAccount.setTotalPage(calculateTotalPages(total, accountFilter.size()));
                    paginatedAccount.setPage(accountFilter.page());
                    return finalClient
                            .bind("userId", userId)
                            .bind("limit", accountFilter.size())
                            .bind("offset", calculateOffSetPagination(accountFilter.size(), accountFilter.page()))
                            .map(AccountRowMapper.toAccount())
                            .all().collectList();
                }).publishOn(Schedulers.boundedElastic()).mapNotNull(listAccounts -> {
                    paginatedAccount.setItems(listAccounts.block());
                    return null;
                })
                .thenReturn(paginatedAccount);
    }

    @Override
    public Mono<Account> findById(String userId, String accountId) {
        return databaseClient.sql(Queries.FIND_BY_ACCOUNT_ID)
                .bind("userId", userId)
                .bind("accountId", accountId)
                .map(AccountRowMapper.toAccount()).one();
    }
}
