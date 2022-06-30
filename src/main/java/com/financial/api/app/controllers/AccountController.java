package com.financial.api.app.controllers;

import com.financial.api.app.mappers.AccountMapper;
import com.financial.api.app.requests.AccountRequest;
import com.financial.api.app.requests.AccountShareRequest;
import com.financial.api.app.responses.AccountResponse;
import com.financial.api.app.responses.AccountShareUserResponse;
import com.financial.api.app.responses.SuccessResponse;
import com.financial.api.domain.account.filter.AccountFilter;
import com.financial.api.domain.account.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users/{userId}/accounts")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @GetMapping
    public Mono<ResponseEntity<?>> getAll(@PathVariable String userId, @RequestParam(name = "description", required = false) String description) {
        List<AccountResponse> responseList = new ArrayList<>();
        AccountFilter filter = new AccountFilter(description);
        return accountService.findAll(userId, filter)
                .map(account -> responseList.add(AccountMapper.toAccountFromAccountResponse(account)))
                .then()
                .thenReturn(ResponseEntity.ok().body(responseList));
    }

    @GetMapping(value = "/{accountId}")
    public Mono<ResponseEntity<?>> getById(@PathVariable String userId, @PathVariable String accountId) {
        return accountService.findById(userId, accountId)
                .map(account -> ResponseEntity.ok().body(account));
    }

    @PostMapping
    public Mono<ResponseEntity<?>> create(@RequestBody @Validated AccountRequest accountRequest, @PathVariable String userId) {
         return accountService.save(
                 userId,
                 AccountMapper.toAccountRequestFromAccount(accountRequest, null)
                ).map(account -> ResponseEntity.status(HttpStatus.CREATED)
                 .body(new SuccessResponse("Account created", account)));
    }

    @PutMapping(value = "/{accountId}")
    public Mono<ResponseEntity<?>> update(@RequestBody AccountRequest accountRequest, @PathVariable String userId, @PathVariable String accountId) {
        return accountService.update(
                userId,
                AccountMapper.toAccountRequestFromAccount(accountRequest, accountId)
        ).map(account -> ResponseEntity.ok().body(new SuccessResponse("Account updated", account)));
    }

    @DeleteMapping(value = "/{accountId}")
    public Mono<ResponseEntity<?>> delete(@PathVariable String userId, @PathVariable String accountId) {
        return accountService.delete(
                userId,
                accountId
        ).thenReturn(ResponseEntity.noContent().build());
    }

    @PostMapping(value = "/{accountId}/sharing")
    public Mono<ResponseEntity<?>> createUserSharing(@RequestBody @Validated AccountShareRequest accountShareRequest, @PathVariable String accountId) {
        return accountService
                .shareAccount(accountShareRequest.sharedUsers(), accountId)
                .then().thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse("successfully shared", null)));
    }

    @GetMapping(value = "/{accountId}/sharing")
    public Mono<ResponseEntity<?>> listAllUserSharing(@PathVariable String userId, @PathVariable String accountId) {
        List<AccountShareUserResponse> list = new ArrayList<>();
        return accountService.listAllShared(userId, accountId)
                .map(accountShareUser -> list.add(AccountMapper.toAccountSharedUserFromAccountSharedUserResponse(accountShareUser)))
                .then()
                .thenReturn(ResponseEntity.ok().body(list));
    }

    @DeleteMapping(value = "/{accountId}/sharing/{userSharingId}")
    public Mono<ResponseEntity<?>> deleteUserSharing(@PathVariable String accountId, @PathVariable String userSharingId) {
        return accountService.deleteSharing(userSharingId, accountId).thenReturn(ResponseEntity.noContent().build());
    }


}
