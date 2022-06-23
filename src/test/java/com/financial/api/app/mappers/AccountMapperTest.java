package com.financial.api.app.mappers;

import com.financial.api.app.requests.AccountRequest;
import com.financial.api.app.responses.AccountResponse;
import com.financial.api.app.responses.AccountShareUserResponse;
import com.financial.api.domain.account.enums.AccountType;
import com.financial.api.domain.account.model.Account;
import com.financial.api.domain.accountUser.model.AccountShareUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AccountMapperTest {

    @Test
    public void testIfCreateToAccountRequestFromAccount() {
        //GIVEN
        String description = "teste";
        Integer initialAmount = 30000;
        String type = "DINHEIRO";
        AccountRequest accountRequest = new AccountRequest(description, initialAmount, type);
        String accountId = "6721e129-d38f-450e-b613-3666a6b94cda";

        //WHEN
            Account account = AccountMapper.toAccountRequestFromAccount(accountRequest, accountId);
        //THEN
        Assertions.assertEquals(accountId, account.id());
        Assertions.assertEquals(description, account.description());
        Assertions.assertEquals(initialAmount, account.initialAmount());
        Assertions.assertEquals(type, account.type().getDescription());
    }

    @Test
    public void testIfCreateToAccountFromAccountResponse() {
        //GIVEN
            String accountId = "6721e129-d38f-450e-b613-3666a6b94cda";
            Integer initialAmount = 30000;
            String description = "teste";
            String type = "DINHEIRO";
            LocalDateTime date = LocalDateTime.now();
            boolean owner = true;
            Account account = new Account(accountId,description,initialAmount, AccountType.valueOf(type),date,date,owner);
        //WHEN
            AccountResponse accountResponse = AccountMapper.toAccountFromAccountResponse(account);
        //THEN
            Assertions.assertEquals(accountId, accountResponse.id());
            Assertions.assertEquals(description, accountResponse.description());
            Assertions.assertEquals(initialAmount, accountResponse.initialAmount());
            Assertions.assertEquals(type, accountResponse.type());
            Assertions.assertEquals(date, accountResponse.createdAt());
            Assertions.assertEquals(date, accountResponse.updatedAt());
            Assertions.assertTrue(accountResponse.owner());
    }

    @Test
    public void testIfCreateToAccountSharedUserFromAccountSharedUserResponse() {
        //GIVEN
            String accountId = "6721e129-d38f-450e-b613-3666a6b94cda";
            String userId = "6721e129-d38f-450e-b613-3666a6b94cda";
            String viewName = "teste";
            AccountShareUser accountShareUser = new AccountShareUser(accountId, userId, viewName);
        //WHEN
            AccountShareUserResponse accountShareUserResponse = AccountMapper.toAccountSharedUserFromAccountSharedUserResponse(accountShareUser);
        //THEN
            Assertions.assertEquals(accountId, accountShareUserResponse.accountId());
            Assertions.assertEquals(userId, accountShareUserResponse.userId());
            Assertions.assertEquals(viewName, accountShareUser.ViewName());
    }

}
