package com.financial.api.domain.user.enums;

public enum UserStatus {
    ACTIVE("ACTIVE"),
    BLOCKED("BLOCKED");

    private String description;

    UserStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
