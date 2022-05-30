package com.financial.api.app.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SuccessResponse(
    @JsonProperty(value = "message")
    String message,
    @JsonProperty(value = "id")
    String id
){}
