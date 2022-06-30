package com.financial.api.domain.global.exceptions;

public class DomainException extends RuntimeException {
    protected DomainException(String message) {
        super(message);
    }
}
