package com.financial.api.app.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AccountRequest(
        @JsonProperty(value = "description")
        String description,
        @JsonProperty(value = "initial_amount")
        Integer initialAmount,
        @JsonProperty(value = "type")
        String Type
) {}
