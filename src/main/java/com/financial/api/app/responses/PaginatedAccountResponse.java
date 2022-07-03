package com.financial.api.app.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record PaginatedAccountResponse(
   @JsonProperty(value = "total_pages")
   Integer totalPages,
   @JsonProperty(value = "page")
   Integer page,
   @JsonProperty(value = "items")
   Object items
){}
