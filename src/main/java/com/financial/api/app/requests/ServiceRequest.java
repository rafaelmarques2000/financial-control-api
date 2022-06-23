package com.financial.api.app.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.financial.api.domain.services.enums.RecurrenceType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public record ServiceRequest(
     @JsonProperty(value = "description")
     @NotBlank(message = "Preencha o campo description")
     String description,
     @JsonProperty(value = "value")
     @NotNull(message = "Preencha o campo value")
     Integer value,
     @JsonProperty(value = "recurrence_type")
     @NotBlank(message = "Preencha o campo recurrence_type")
     @Pattern(
             regexp = "^MONTHLY|YEARLY$",
             message = "Valor não permitido para o campo recurrence_type"
     )
     String recurrenceType,
     @JsonProperty(value = "account_destiny")
     @NotBlank(message = "Preencha o campo account_destiny")
     String accountId,

      @JsonProperty(value = "status")
     @NotBlank(message = "Preencha o campo status")
      @Pattern(
             regexp = "^ACTIVE|INACTIVE$",
             message = "Valor não permitido para o campo status"
      )
     String status
){}
