package com.financial.api.domain.user.model;

import com.financial.api.domain.user.enums.UserStatus;

import java.util.Date;

public record User(
     String id,
     String login,
     String password,
     String viewName,
     Date createdAt,
     Date updatedAt,
     UserStatus status
){}
