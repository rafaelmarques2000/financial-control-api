package com.financial.api.app.controllers;

import com.financial.api.app.mappers.ServiceMapper;
import com.financial.api.app.requests.ServiceRequest;
import com.financial.api.app.responses.ServiceResponse;
import com.financial.api.domain.services.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users/{userId}/services")
public class ServicesController {
    @Autowired
    private IService service;

    @PostMapping
    public Mono<ResponseEntity<?>> create(@PathVariable String userId, @RequestBody @Validated ServiceRequest serviceRequest) {
        return service.save(userId, ServiceMapper.toServiceRequestFromServiceModel(userId, serviceRequest, null))
                .map(savedService -> ResponseEntity.status(HttpStatus.CREATED).body(
                    ServiceMapper.toServiceModelFromServiceResponse(savedService)
                ));
    }

    @PutMapping(value = "/{serviceId}")
    public Mono<ResponseEntity<?>> update(@PathVariable String userId,@PathVariable String serviceId, @RequestBody @Validated ServiceRequest serviceRequest) {
        return service.update(userId, ServiceMapper.toServiceRequestFromServiceModel(userId, serviceRequest, serviceId))
                .map(updatedService -> ResponseEntity.ok().body(
                        ServiceMapper.toServiceModelFromServiceResponse(updatedService)
                ));
    }

    @DeleteMapping(value = "/{serviceId}")
    public Mono<ResponseEntity<?>> delete(@PathVariable String userId,@PathVariable String serviceId) {
        return service.delete(userId, serviceId)
                .thenReturn(ResponseEntity.noContent().build());
    }

    @GetMapping
    public Mono<ResponseEntity<?>> getAll(@PathVariable String userId) {
        List<ServiceResponse> responseList = new ArrayList<>();
        return service.findAll(userId)
                .map(service -> responseList.add(ServiceMapper.toServiceModelFromServiceResponse(service)))
                .then().thenReturn(ResponseEntity.ok().body(responseList));

    }

    @GetMapping(value = "/{serviceId}")
    public Mono<ResponseEntity<?>> getById(@PathVariable String userId,@PathVariable String serviceId) {
        return service.findById(userId, serviceId)
                .map(service -> ResponseEntity.ok().body(ServiceMapper.toServiceModelFromServiceResponse(service)));
    }
}
