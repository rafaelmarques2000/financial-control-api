package com.financial.api.domain.global.exceptions;

public class IlegalOperationException  extends DomainException{
    protected IlegalOperationException(String message) {
        super(message);
    }
}
