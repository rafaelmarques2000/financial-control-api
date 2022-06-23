package com.financial.api.domain.exceptions;

public class IlegalOperationException  extends DomainException{
    protected IlegalOperationException(String message) {
        super(message);
    }
}
