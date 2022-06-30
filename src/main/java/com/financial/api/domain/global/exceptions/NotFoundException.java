package com.financial.api.domain.global.exceptions;

public class NotFoundException extends DomainException{
    protected NotFoundException(String message) {
        super(message);
    }
}
