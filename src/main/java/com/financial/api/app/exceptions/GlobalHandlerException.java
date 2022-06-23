package com.financial.api.app.exceptions;

import com.financial.api.app.responses.ErrorResponse;
import com.financial.api.app.responses.ValidationErrorResponse;
import com.financial.api.domain.exceptions.IlegalOperationException;
import com.financial.api.domain.exceptions.NotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Component
public class GlobalHandlerException {

    @ExceptionHandler({Throwable.class})
    public ResponseEntity<?> handlerApiException(Throwable exception) {

        if(exception instanceof NotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(exception.getMessage()));
        }

        if(exception instanceof IlegalOperationException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(exception.getMessage()));
        }

        if(exception instanceof WebExchangeBindException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ValidationErrorResponse(((WebExchangeBindException) exception).getAllErrors()).format());
        }

        if(exception instanceof DataIntegrityViolationException) {
            if(exception.getMessage().contains("account_indepontent_share")) {
                return ResponseEntity.badRequest().body(new ErrorResponse("conta ou grupo de contas ja foi compartilhado"));
            }
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(exception.getMessage()));
    }


}