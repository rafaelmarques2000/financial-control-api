package com.financial.api.domain.exceptions;

public class NotFoundException extends DomainException{
    protected NotFoundException(String message) {
        super(message);
    }
}
