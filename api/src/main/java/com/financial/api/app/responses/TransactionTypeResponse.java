package com.financial.api.app.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.financial.api.domain.transaction.model.TransactionCategory;
import com.financial.api.domain.transaction.model.TransactionType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TransactionTypeResponse(
    @JsonProperty(value = "id")
    String id,
    @JsonProperty(value = "description")
    String description
){}
