package com.financial.api.app.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public record TransactionRequest(
        @JsonProperty(value = "description")
        String description,
        @JsonProperty(value = "date")
        LocalDate date,
        @JsonProperty(value = "value")
        Integer value,
        @JsonProperty(value = "extra_description")
        String extraDescription,
        @JsonProperty(value = "transaction_type_id")
        String transactionTypeId,
        @JsonProperty(value = "transaction_category_id")
        String transactionCategoryId
) {}
