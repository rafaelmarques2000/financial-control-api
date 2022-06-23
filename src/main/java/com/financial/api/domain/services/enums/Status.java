package com.financial.api.domain.services.enums;

public enum Status {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
