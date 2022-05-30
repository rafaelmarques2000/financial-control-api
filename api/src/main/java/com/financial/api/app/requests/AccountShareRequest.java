package com.financial.api.app.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record AccountShareRequest(
        @JsonProperty(value = "users")
        List<String> sharedUsers
) {}
