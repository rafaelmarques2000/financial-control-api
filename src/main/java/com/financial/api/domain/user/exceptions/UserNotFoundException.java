package com.financial.api.domain.user.exceptions;

import com.financial.api.domain.global.exceptions.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String message){
        super(message);
    }
}
