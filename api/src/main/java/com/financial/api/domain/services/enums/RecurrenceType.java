package com.financial.api.domain.services.enums;

public enum RecurrenceType {
    MONTHLY("MONTHLY"),
    YEARLY("YEARLY");

    private String description;

    RecurrenceType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
