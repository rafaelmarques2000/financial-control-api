package com.financial.api.domain.transaction.filter;

import java.time.LocalDate;

public class TransactionFilter {

    private LocalDate beginDate;
    private LocalDate endDate;

    private String categoryId;

    private String typeId;

    public TransactionFilter formatParams(String beginDate, String endDate, String categoryId, String typeId) {
        this.beginDate = beginDate != null ? LocalDate.parse(beginDate) : null;
        this.endDate = endDate != null ? LocalDate.parse(endDate) : null;
        this.categoryId = categoryId;
        this.typeId = typeId;
        return this;
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