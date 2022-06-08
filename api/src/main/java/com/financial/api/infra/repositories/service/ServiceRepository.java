package com.financial.api.infra.repositories.service;

import com.financial.api.domain.services.model.Service;
import com.financial.api.domain.services.repository.IServiceRepository;
import com.financial.api.infra.repositories.AbstractRepository;
import com.financial.api.infra.repositories.service.mapper.ServiceRowMapper;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

public class ServiceRepository extends AbstractRepository implements IServiceRepository {

    public ServiceRepository(DatabaseClient databaseClient, TransactionalOperator operator) {
        super(databaseClient, operator);
    }

    @Override
    public Mono<Service> save(String userId, Service service) {
        return databaseClient.sql(Queries.INSERT_SERVICE)
                .bind("id", UUID.fromString(service.id()))
                .bind("description", service.description())
                .bind("value", service.value())
                .bind("recurrenceType", service.recurrenceType())
                .bind("userId", UUID.fromString(service.userId()))
                .bind("accountId", UUID.fromString(service.accountId()))
                .bind("createdAt", service.createdAt())
                .bind("updatedAt", service.updatedAt())
                .bind("status", service.status())
                .then().thenReturn(service);
    }

    @Override
    public Mono<String> update(String userId, Service service) {
        return databaseClient.sql(Queries.UPDATE_SERVICE)
                .bind("description", service.description())
                .bind("value", service.value())
                .bind("recurrenceType", service.recurrenceType())
                .bind("userId", UUID.fromString(service.userId()))
                .bind("accountId", UUID.fromString(service.accountId()))
                .bind("updatedAt", service.updatedAt())
                .bind("status", service.status())
                .bind("serviceId", UUID.fromString(service.id()))
                .then().thenReturn(service.id());
    }

    @Override
    public Mono<Void> delete(String userId, String serviceId) {
        return databaseClient.sql(Queries.SOFT_DELETE_SERVICE)
                .bind("userId", UUID.fromString(userId))
                .bind("serviceId", UUID.fromString(serviceId))
                .bind("deletedAt", LocalDateTime.now())
                .then();
    }

    @Override
    public Flux<Service> findAll(String userId) {
        return databaseClient.sql(Queries.FIND_ALL_SERVICES_BY_USER)
                .bind("userId", UUID.fromString(userId))
                .map(ServiceRowMapper.toRowFromService()).all();
    }

    @Override
    public Flux<Service> findAll() {
        return databaseClient.sql(Queries.FIND_ALL_SERVICES)
                .map(ServiceRowMapper.toRowFromService()).all();
    }

    @Override
    public Mono<Service> findById(String userId, String serviceId) {
        return databaseClient.sql(Queries.FIND_SERVICE_BY_ID)
                .bind("userId", UUID.fromString(userId))
                .bind("serviceId", UUID.fromString(serviceId))
                .map(ServiceRowMapper.toRowFromService()).one();
    }
}
