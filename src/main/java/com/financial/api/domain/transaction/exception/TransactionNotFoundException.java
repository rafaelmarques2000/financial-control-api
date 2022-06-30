package com.financial.api.domain.transaction.exception;

import com.financial.api.domain.global.exceptions.NotFoundException;

public class TransactionNotFoundException extends NotFoundException {
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
