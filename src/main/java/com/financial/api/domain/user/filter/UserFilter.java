package com.financial.api.domain.user.filter;

import java.time.LocalDate;

public class UserFilter {
    private String username;

    public UserFilter formatParams(String username) {
        this.username = username;
        return this;
    }

    public boolean hasFilter() {
        return username != null;
    }

    public String getUsername() {
        return username;
    }
}