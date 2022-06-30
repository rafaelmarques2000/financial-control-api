package com.financial.api.domain.account.service;

import com.financial.api.domain.account.exception.AccountNotFoundException;
import com.financial.api.domain.account.exception.IlegalAccountOperationException;
import com.financial.api.domain.account.filter.AccountFilter;
import com.financial.api.domain.account.model.Account;
import com.financial.api.domain.account.repository.IAccountRepository;
import com.financial.api.domain.accountUser.model.AccountShareUser;
import com.financial.api.domain.accountUser.repository.IAccountUserRepository;
import com.financial.api.domain.user.service.IUserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class AccountService implements IAccountService{

    private IAccountRepository accountRepository;
    private IAccountUserRepository accountUserRepository;

    private IUserService userService;

    public AccountService(IAccountRepository accountRepository, IUserService userService, IAccountUserRepository accountUserRepository) {
        this.accountRepository = accountRepository;
        this.userService = userService;
        this.accountUserRepository = accountUserRepository;
    }

    @Override
    public Mono<Account> save(String userId, Account account) {
        return accountRepository.save(userId,account)
                .then(accountRepository.findById(userId, account.id()));
    }

    @Override
    public Mono<Account> update(String userId, Account account) {
        return accountRepository.findById(userId, account.id())
                .switchIfEmpty(Mono.error(new AccountNotFoundException("Account not found")))
                .map(findAccount -> {
                   if(!findAccount.owner()) {
                       throw new IlegalAccountOperationException("Não é possivel atualizar uma conta cujo você não é proprietario.");
                   }
                   return  findAccount;
                })
                .then(accountRepository.update(userId, account))
                .then(accountRepository.findById(userId, account.id()));
    }

    @Override
    public Mono<Void> delete(String userId, String accountId) {
         return accountRepository.findById(userId, accountId)
                 .switchIfEmpty(Mono.error(new AccountNotFoundException("Account not found")))
                 .map(account -> {
                     if(!account.owner()) {
                         throw new IlegalAccountOperationException("Não é possivel deletar uma conta que você não seja o proprietario");
                     }
                     return account;
                 })
                 .flatMap(account -> accountRepository.delete(userId,account.id()));
    }

    @Override
    public Flux<Account> findAll(String userId, AccountFilter accountFilter) {
        return accountRepository.findAll(userId, accountFilter);
    }

    @Override
    public Mono<Account> findById(String userId, String accountId) {
        return accountRepository.findById(userId, accountId)
                .switchIfEmpty(Mono.error(new AccountNotFoundException("Account not found")));
    }

    @Override
    public Flux<Void> shareAccount(List<String> sharedUsersId, String accountId) {
         return Flux.fromArray(sharedUsersId.toArray())
                 .flatMap(userSharedId -> userService.findById((String)userSharedId))
                 .flatMap(user -> accountUserRepository.shareAccount(user.id(), accountId))
                 .then().flux();
    }

    @Override
    public Flux<AccountShareUser> listAllShared(String userId, String accountId) {
        return accountUserRepository.listAllSharing(userId, accountId);
    }

    @Override
    public Mono<Void> deleteSharing(String userSharingId, String accountId) {
        return accountUserRepository.deleteSharing(userSharingId, accountId);
    }
}
