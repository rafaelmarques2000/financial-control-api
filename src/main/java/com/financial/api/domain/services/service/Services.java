package com.financial.api.domain.services.service;

import com.financial.api.domain.services.exception.ServiceNotFoundException;
import com.financial.api.domain.services.model.Service;
import com.financial.api.domain.services.repository.IServiceRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Services implements IService{

    private IServiceRepository serviceRepository;

    public Services(IServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Mono<Service> save(String userId, Service service) {
        return serviceRepository.save(userId, service);
    }

    @Override
    public Mono<Service> update(String userId, Service service) {
        return serviceRepository
                .findById(userId, service.id())
                .switchIfEmpty(Mono.error(new ServiceNotFoundException("Serviço não encontrado")))
                .then(serviceRepository.update(userId, service))
                .then(serviceRepository.findById(userId, service.id()));
    }

    @Override
    public Mono<Void> delete(String userId, String serviceId) {
        return serviceRepository
                .findById(userId, serviceId)
                .switchIfEmpty(Mono.error(new ServiceNotFoundException("Serviço não encontrado")))
                .then(serviceRepository.delete(userId, serviceId));
    }

    @Override
    public Flux<Service> findAll(String userId) {
        return serviceRepository.findAll(userId);
    }

    @Override
    public Mono<Service> findById(String userId, String serviceId) {
        return serviceRepository.findById(userId, serviceId)
                .switchIfEmpty(Mono.error(new ServiceNotFoundException("Serviço não encontrado")));
    }
}
