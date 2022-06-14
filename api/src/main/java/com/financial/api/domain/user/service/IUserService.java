package com.financial.api.domain.user.service;

import com.financial.api.domain.user.filter.UserFilter;
import com.financial.api.domain.user.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserService {
    Mono<User> findByUsername(String username);
    Mono<User> findById(String id);
    Flux<User> findAll(UserFilter userFilter);
}
