package com.financial.api.domain.account.filter;

import com.financial.api.domain.global.interfaces.IFilter;

public class AccountFilter implements IFilter {

    private final String description;

    public AccountFilter(String description) {
        this.description = description;
    }

    @Override
    public boolean hasFilter() {
        return description != null;
    }

    public String getDescription() {
        return description;
    }
}
