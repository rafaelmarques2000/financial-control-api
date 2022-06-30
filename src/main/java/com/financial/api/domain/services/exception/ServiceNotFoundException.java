package com.financial.api.domain.services.exception;

import com.financial.api.domain.global.exceptions.NotFoundException;

public class ServiceNotFoundException extends NotFoundException {

    public ServiceNotFoundException(String message) {
        super(message);
    }
}
