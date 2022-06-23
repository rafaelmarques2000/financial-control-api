package com.financial.api.app.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponse(
   @JsonProperty(value = "id")
   String id,
   @JsonProperty(value = "view_name")
   String viewName
){}
