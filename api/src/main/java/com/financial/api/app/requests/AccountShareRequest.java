package com.financial.api.app.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public record AccountShareRequest(
        @JsonProperty(value = "users")
        @NotBlank(message = "Informe os usuários para compartilhamento da conta")
        List<String> sharedUsers
) {}
