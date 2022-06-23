package com.financial.api.app.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.financial.api.domain.transaction.model.TransactionCategory;
import com.financial.api.domain.transaction.model.TransactionType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TransactionResponse(
    @JsonProperty(value = "id")
    String id,
    @JsonProperty(value = "description")
    String description,
    @JsonProperty(value = "date")
    LocalDate date,
    @JsonProperty(value = "value")
    Integer value,
    @JsonProperty(value = "extra_description")
    String extraDescription,
    @JsonProperty(value = "account_id")
    String accountId,
    @JsonProperty(value = "type")
    TransactionType type,
    @JsonProperty(value = "category")
    TransactionCategory category,
    @JsonProperty(value = "created_at")
    LocalDateTime created_at,
    @JsonProperty(value = "updated_at")
    LocalDateTime updated_at
){}
