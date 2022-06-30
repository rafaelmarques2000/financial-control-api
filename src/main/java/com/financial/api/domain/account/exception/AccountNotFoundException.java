package com.financial.api.domain.account.exception;

import com.financial.api.domain.global.exceptions.NotFoundException;

public class AccountNotFoundException extends NotFoundException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
