package com.financial.api.domain.account.filter;

import com.financial.api.domain.global.interfaces.IFilter;

public record AccountFilter(String description, Integer size, Integer page) implements IFilter {

    @Override
    public boolean hasFilter() {
        return description != null;
    }
}
