package com.financial.api.domain.account.enums;

public enum AccountType {
    CORRENTE("CORRENTE"),
    POUPANCA("POUPANCA"),
    DINHEIRO("DINHEIRO"),
    CARTAO_CREDITO("CARTAO_CREDITO"),
    INVESTIMENTO("INVESTIMENTO");

    private String description;

    AccountType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
