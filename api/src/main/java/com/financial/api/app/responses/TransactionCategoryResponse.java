package com.financial.api.app.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TransactionCategoryResponse(
    @JsonProperty(value = "id")
    String id,
    @JsonProperty(value = "description")
    String description
){}
