package com.financial.api.domain.global.model;

import java.util.List;

public class AbstractPaginationResult {
    private Double totalPage;
    private Integer page;
    private Object items;

    public Double getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Double totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Object getItems() {
        return items;
    }

    public void setItems(Object items) {
        this.items = items;
    }
}
