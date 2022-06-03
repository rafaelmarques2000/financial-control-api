package com.financial.api.app.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public record LoginRequest(
        @JsonProperty(value = "username")
        @NotBlank(message="Informe seu usuário")
        String username,
        @JsonProperty(value = "password")
        @NotBlank(message="Informe sua senha")
        String password
) {}
