package com.financial.api.domain.account.exception;

import com.financial.api.domain.global.exceptions.IlegalOperationException;

public class IlegalAccountOperationException extends IlegalOperationException {
    public IlegalAccountOperationException(String message) {
        super(message);
    }
}
