package com.financial.api.domain.user.repository;

import com.financial.api.domain.user.model.User;
import reactor.core.publisher.Mono;

public interface IUserRepository {
    Mono<User> findByUsername(String username);
    Mono<User> findById(String Id);
}
