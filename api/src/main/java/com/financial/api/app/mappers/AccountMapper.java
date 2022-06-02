package com.financial.api.app.mappers;

import com.financial.api.app.requests.AccountRequest;
import com.financial.api.app.responses.AccountResponse;
import com.financial.api.app.responses.AccountShareUserResponse;
import com.financial.api.app.responses.UserResponse;
import com.financial.api.domain.account.enums.AccountType;
import com.financial.api.domain.account.model.Account;
import com.financial.api.domain.accountUser.model.AccountShareUser;
import com.financial.api.domain.user.model.User;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class AccountMapper {

    public static Account toAccountRequestFromAccount(AccountRequest accountRequest, String accountId) {
        return new Account(
                 accountId == null ? UUID.randomUUID().toString() : accountId,
                 accountRequest.description(),
                 accountRequest.initialAmount(),
                 AccountType.valueOf(accountRequest.Type()),
                 LocalDateTime.now(),
                 LocalDateTime.now()
         );
    }

    public static AccountResponse toAccountFromAccountResponse(Account account) {
        return new AccountResponse(
                account.id(),
                account.description(),
                account.initialAmount(),
                account.type().getDescription(),
                account.createdAt(),
                account.updatedAt()
        );
    }

    public static AccountShareUserResponse toAccountSharedUserFromAccountSharedUserResponse(AccountShareUser accountShareUser) {
         return new AccountShareUserResponse(
                 accountShareUser.AccountId(),
                 accountShareUser.UserId(),
                 accountShareUser.ViewName()
         );
    }

}
