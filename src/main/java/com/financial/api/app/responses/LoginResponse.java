package com.financial.api.app.responses;

public record LoginResponse(
    String userId,
    String token
){}
