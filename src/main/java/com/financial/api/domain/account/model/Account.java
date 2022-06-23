package com.financial.api.domain.account.model;

import com.financial.api.domain.account.enums.AccountType;

import java.time.LocalDateTime;

public record Account(
     String id,
     String description,
     Integer initialAmount,
     AccountType type,
     LocalDateTime createdAt,
     LocalDateTime updatedAt,
     Boolean owner
) {}
