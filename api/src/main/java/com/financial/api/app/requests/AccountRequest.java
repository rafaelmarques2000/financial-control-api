package com.financial.api.app.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public record AccountRequest(
        @JsonProperty(value = "description")
        @NotBlank(message = "Preencha o campo description")
        String description,
        @JsonProperty(value = "initial_amount")
        @NotNull(message = "Preencha o campo initial amount")
        Integer initialAmount,
        @JsonProperty(value = "type")
        @NotBlank(message = "Preencha o campo type")
        @Pattern(
                regexp = "^CORRENTE|POUPANCA|DINHEIRO|CARTAO_CREDITO|INVESTIMENTO*",
                message = "Valor não permitido para o campo type"
        )
        String Type
) {}
