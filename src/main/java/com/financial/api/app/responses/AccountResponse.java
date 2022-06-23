package com.financial.api.app.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record AccountResponse(
   @JsonProperty(value = "id")
   String id,
   @JsonProperty(value = "description")
   String description,
   @JsonProperty(value = "initial_amount")
   Integer initialAmount,
   @JsonProperty(value = "type")
   String type,
   @JsonProperty(value = "owner")
   Boolean owner,
   @JsonProperty(value = "created_at")
   LocalDateTime createdAt,
   @JsonProperty(value = "updated_at")
   LocalDateTime updatedAt
){}
