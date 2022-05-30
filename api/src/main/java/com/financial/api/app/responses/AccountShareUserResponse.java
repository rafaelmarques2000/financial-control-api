package com.financial.api.app.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record AccountShareUserResponse(
   @JsonProperty(value = "account_id")
   String accountId,
   @JsonProperty(value = "user_id")
   String userId,
   @JsonProperty(value = "view_name")
   String ViewName
){}
