package com.financial.api.domain.exceptions;

public class DomainException extends RuntimeException {
    protected DomainException(String message) {
        super(message);
    }
}
