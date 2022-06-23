package com.financial.api.domain.account.repository;

import com.financial.api.domain.account.model.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAccountRepository {
    Mono<Account> save(String userId, Account account);
    Mono<Account> update(String userId, Account account);
    Mono<Void> delete(String userId, String accountId);
    Flux<Account> findAll(String userId);
    Mono<Account> findById(String userId,String accountId);

}
