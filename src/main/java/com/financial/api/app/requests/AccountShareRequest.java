package com.financial.api.app.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public record AccountShareRequest(
        @JsonProperty(value = "users")
        @NotEmpty(message = "Informe a lista de usuários para compartilhamento")
        List<String> sharedUsers
) {}
