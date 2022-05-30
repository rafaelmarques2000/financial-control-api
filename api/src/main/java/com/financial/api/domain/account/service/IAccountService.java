package com.financial.api.domain.account.service;

import com.financial.api.domain.account.model.Account;
import com.financial.api.domain.accountUser.model.AccountShareUser;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IAccountService {
    Mono<Account> save(String userId, Account account);
    Mono<Account> update(String userId, Account account);
    Mono<Void> delete(String userId, String accountId);
    Flux<Account> findAll(String userId);
    Mono<Account> findById(String userId,String accountId);

    Flux<Void> shareAccount(List<String> sharedUsersId, String accountId);

    Flux<AccountShareUser> listAllShared(String userId, String accountId);

}
