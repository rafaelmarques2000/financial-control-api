package com.financial.api.domain.services.service;

import com.financial.api.domain.services.model.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IService {

    Mono<Service> save(String userId, Service service);
    Mono<Service> update(String userId, Service service);
    Mono<Void> delete(String userId, String serviceId);
    Flux<Service> findAll(String userId);
    Mono<Service> findById(String userId,String serviceId);

}
