package com.financial.api.domain.accountUser.repository;

import com.financial.api.domain.accountUser.model.AccountShareUser;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAccountUserRepository {
    Mono<String> shareAccount(String userShareId, String accountId);
    Flux<AccountShareUser> listAllSharing(String userId, String accountId);
    Mono<Void> deleteSharing(String userSharingId, String accountId);
}
