package com.financial.api.app.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public record TransactionRequest(
        @JsonProperty(value = "description")
        @NotBlank(message = "Informe a descrição da transação")
        String description,
        @JsonProperty(value = "date")
        @NotNull(message = "Informe a data de transação")
        LocalDate date,
        @JsonProperty(value = "value")
        @NotNull(message = "Informe o valor")
        Integer value,
        @JsonProperty(value = "extra_description", defaultValue = "")
        String extraDescription,
        @JsonProperty(value = "transaction_type_id")
        @NotBlank(message = "informe o tipo de transação")
        @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "Tipo de transação inválido")
        String transactionTypeId,
        @JsonProperty(value = "transaction_category_id")
        @NotBlank(message = "informe a categoria da transação")
        @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", message = "Categoria inválida")
        String transactionCategoryId
) {}
