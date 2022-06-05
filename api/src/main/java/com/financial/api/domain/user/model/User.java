package com.financial.api.domain.user.model;

import com.financial.api.domain.user.enums.UserStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record User(
     String id,
     String login,
     String password,
     String viewName,
     LocalDateTime createdAt,
     LocalDateTime updatedAt,
     UserStatus status
){}
