package com.financial.api.domain.services.repository;

import com.financial.api.domain.account.model.Account;
import com.financial.api.domain.services.model.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IServiceRepository {
    Mono<Service> save(String userId, Service service);
    Mono<String> update(String userId, Service account);
    Mono<Void> delete(String userId, String serviceId);
    Flux<Service> findAll(String userId);
    Flux<Service> findAll();
    Mono<Service> findById(String userId,String serviceId);
}
