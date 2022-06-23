package com.financial.api.domain.account.service;

import com.financial.api.domain.account.enums.AccountType;
import com.financial.api.domain.account.exception.AccountNotFoundException;
import com.financial.api.domain.account.exception.IlegalAccountOperationException;
import com.financial.api.domain.account.model.Account;
import com.financial.api.domain.user.service.UserService;
import com.financial.api.infra.repositories.account.AccountRepository;
import com.financial.api.infra.repositories.accountUser.AccountUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    AccountRepository accountRepository;

    @Mock
    AccountUserRepository accountUserRepository;

    @Mock
    UserService userService;


    @Test
    public void testIfCreateAccount() {
        //GIVEN
        String userId = "2d2f06c0-dbaf-406a-b1db-a9c2f130ce0c";

        String accountId = "d77d45df-20c5-4e74-b2e2-0e86f926419f";
        String description = "teste";
        Integer initialAmount = 123456;
        AccountType accountType = AccountType.CORRENTE;
        LocalDateTime createOrUpdateDate = LocalDateTime.now();
        boolean owner = true;

        Account account = new Account(accountId,description,initialAmount,accountType,createOrUpdateDate, createOrUpdateDate,owner);
        Mockito.when(accountRepository.save(userId, account)).thenReturn(Mono.just(account));
        Mockito.when(accountRepository.findById(userId, accountId)).thenReturn(Mono.just(account));

        StepVerifier.create(accountService.save(userId, account))
                .consumeNextWith(account1 -> {
                    Assertions.assertEquals(accountId, account1.id());
                    Assertions.assertEquals(description, account1.description());
                    Assertions.assertEquals(initialAmount, account1.initialAmount());
                    Assertions.assertEquals(accountType.getDescription(), account1.type().getDescription());
                    Assertions.assertEquals(createOrUpdateDate, account1.createdAt());
                    Assertions.assertEquals(createOrUpdateDate, account1.updatedAt());
                    Assertions.assertTrue(account1.owner());
                })
                .verifyComplete();
    }

    @Test
    public void testIfUpdateAccountErrorAccountNotFound() {

        //GIVEN
        String userId = "2d2f06c0-dbaf-406a-b1db-a9c2f130ce0c";

        String accountId = "d77d45df-20c5-4e74-b2e2-0e86f926419f";
        String description = "teste";
        Integer initialAmount = 123456;
        AccountType accountType = AccountType.CORRENTE;
        LocalDateTime createOrUpdateDate = LocalDateTime.now();
        boolean owner = true;

        Account account = new Account(accountId,description,initialAmount,accountType,createOrUpdateDate, createOrUpdateDate,owner);

        Mockito.when(accountRepository.findById(userId, accountId)).thenReturn(Mono.empty());
        Mockito.when(accountRepository.update(userId, account)).thenReturn(Mono.just(account));
        Mono<Account> update = accountService.update(userId,account);

        StepVerifier.create(update)
                .expectError(AccountNotFoundException.class)
                .verify();
    }

    @Test
    public void testIfAccountErrorNotAccountOwner() {

        //GIVEN
        String userId = "2d2f06c0-dbaf-406a-b1db-a9c2f130ce0c";

        String accountId = "d77d45df-20c5-4e74-b2e2-0e86f926419f";
        String description = "teste";
        Integer initialAmount = 123456;
        AccountType accountType = AccountType.CORRENTE;
        LocalDateTime createOrUpdateDate = LocalDateTime.now();
        boolean owner = false;

        Account account = new Account(accountId,description,initialAmount,accountType,createOrUpdateDate, createOrUpdateDate,owner);

        Mockito.when(accountRepository.findById(userId, accountId)).thenReturn(Mono.just(account));
        Mockito.when(accountRepository.update(userId, account)).thenReturn(Mono.just(account));
        Mono<Account> update = accountService.update(userId,account);

        StepVerifier.create(update)
                .expectError(IlegalAccountOperationException.class)
                .verify();
    }

    @Test
    public void testIfAccountUpdated() {

        //GIVEN
        String userId = "2d2f06c0-dbaf-406a-b1db-a9c2f130ce0c";

        String accountId = "d77d45df-20c5-4e74-b2e2-0e86f926419f";
        String description = "teste";
        Integer initialAmount = 123456;
        AccountType accountType = AccountType.CORRENTE;
        LocalDateTime createOrUpdateDate = LocalDateTime.now();
        boolean owner = true;

        Account account = new Account(accountId,description,initialAmount,accountType,createOrUpdateDate, createOrUpdateDate,owner);

        Mockito.when(accountRepository.findById(userId, accountId)).thenReturn(Mono.just(account));
        Mockito.when(accountRepository.update(userId, account)).thenReturn(Mono.just(account));
        Mono<Account> update = accountService.update(userId,account);

        StepVerifier.create(update)
                .consumeNextWith(account1 -> {
                    Assertions.assertEquals(accountId, account1.id());
                    Assertions.assertEquals(description, account1.description());
                    Assertions.assertEquals(initialAmount, account1.initialAmount());
                    Assertions.assertEquals(accountType.getDescription(), account1.type().getDescription());
                    Assertions.assertEquals(createOrUpdateDate, account1.createdAt());
                    Assertions.assertEquals(createOrUpdateDate, account1.updatedAt());
                    Assertions.assertTrue(account1.owner());
                })
                .verifyComplete();
    }

    //@TODO Parei aqui

}
