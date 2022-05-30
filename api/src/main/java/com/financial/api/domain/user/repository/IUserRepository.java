package com.financial.api.domain.user.repository;

import com.financial.api.domain.user.model.User;
import reactor.core.publisher.Mono;

public interface IUserRepository {
    Mono<User> findByUsernameAndPassword(String username , String password);
    Mono<User> findById(String Id);
}
