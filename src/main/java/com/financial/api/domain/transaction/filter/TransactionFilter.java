package com.financial.api.domain.transaction.filter;

import com.financial.api.domain.global.interfaces.IFilter;

import java.time.LocalDate;

public class TransactionFilter implements IFilter {

    private final LocalDate beginDate;
    private final LocalDate endDate;

    private final String categoryId;

    private final String typeId;

    public TransactionFilter(String beginDate, String endDate, String categoryId, String typeId) {
        this.beginDate = beginDate != null ? LocalDate.parse(beginDate) : null;
        this.endDate = endDate != null ? LocalDate.parse(endDate) : null;
        this.categoryId = categoryId;
        this.typeId = typeId;
    }

    public boolean hasFilter() {
        return beginDate != null || endDate != null || categoryId != null || typeId != null;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getTypeId() {
        return typeId;
    }
}