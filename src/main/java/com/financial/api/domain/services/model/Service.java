package com.financial.api.domain.services.model;

import com.financial.api.domain.services.enums.RecurrenceType;
import com.financial.api.domain.services.enums.Status;

import java.time.LocalDateTime;

public record Service(
     String id,
     String description,
     Integer value,
     RecurrenceType recurrenceType,
     String userId,
     String accountId,
     LocalDateTime createdAt,
     LocalDateTime updatedAt,
     Status status
){}
