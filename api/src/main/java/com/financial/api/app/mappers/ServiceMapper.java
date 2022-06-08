package com.financial.api.app.mappers;

import com.financial.api.app.requests.ServiceRequest;
import com.financial.api.app.responses.ServiceResponse;
import com.financial.api.domain.services.enums.RecurrenceType;
import com.financial.api.domain.services.enums.Status;
import com.financial.api.domain.services.model.Service;

import java.time.LocalDateTime;
import java.util.UUID;

public class ServiceMapper {

    public static Service toServiceRequestFromServiceModel(String userId, ServiceRequest serviceRequest, String serviceId) {
          return new Service(
                  serviceId == null ? UUID.randomUUID().toString() : serviceId,
                  serviceRequest.description(),
                  serviceRequest.value(),
                  RecurrenceType.valueOf(serviceRequest.recurrenceType()),
                  userId,
                  serviceRequest.accountId(),
                  LocalDateTime.now(),
                  LocalDateTime.now(),
                  Status.valueOf(serviceRequest.status())
          );
    }

    public static ServiceResponse toServiceModelFromServiceResponse(Service service) {
        return new ServiceResponse(
                service.id(),
                service.description(),
                service.value(),
                service.recurrenceType().getDescription(),
                service.userId(),
                service.accountId(),
                service.createdAt(),
                service.updatedAt(),
                service.status().getDescription()
        );
    }
}
