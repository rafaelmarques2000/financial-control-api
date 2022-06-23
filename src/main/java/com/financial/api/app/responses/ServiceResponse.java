package com.financial.api.app.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public record ServiceResponse(

     @JsonProperty(value = "id")
     String id,
     @JsonProperty(value = "description")
     String description,
     @JsonProperty(value = "value")
     Integer value,
     @JsonProperty(value = "recurrence_type")
     String recurrenceType,
     @JsonProperty(value = "user_id")
     String userId,
     @JsonProperty(value = "account_destiny")
     String accountId,
     @JsonProperty(value = "created_at")
     LocalDateTime createdAt,
     @JsonProperty(value = "updated_at")
     LocalDateTime updatedAt,
     @JsonProperty(value = "status")
     String status
){}
