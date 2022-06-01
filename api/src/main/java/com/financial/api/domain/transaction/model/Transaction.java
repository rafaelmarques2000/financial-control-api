package com.financial.api.domain.transaction.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record Transaction(
        String id,
        String description,
        LocalDate date,
        Integer value,
        String extraDescription,
        String accountId,
        String transactionTypeId,
        String transactionCategoryId,
        LocalDateTime created_at,
        LocalDateTime updated_at
) {}
