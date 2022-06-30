package com.financial.api.domain.account.repository;

import com.financial.api.domain.account.filter.AccountFilter;
import com.financial.api.domain.account.model.Account;
import com.financial.api.domain.global.interfaces.IFilter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAccountRepository {
    Mono<Account> save(String userId, Account account);
    Mono<Account> update(String userId, Account account);
    Mono<Void> delete(String userId, String accountId);
    Flux<Account> findAll(String userId, AccountFilter filter);
    Mono<Account> findById(String userId,String accountId);

}
