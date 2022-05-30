package com.financial.api.app.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public record LoginRequest(
        @JsonProperty(value = "username")
        @NotBlank(message="Username is mandatory")
        String username,
        @JsonProperty(value = "password")
        @NotBlank(message="Password is mandatory")
        String password
) {}
