package com.financial.api.app.responses;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse {

    private List<ObjectError> validationErrors;

    public ValidationErrorResponse(List<ObjectError> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public List<ErrorResponse> format() {
        List<ErrorResponse> validationErrorsFormatted = new ArrayList<>();
        validationErrors.forEach(objectError -> {
               validationErrorsFormatted.add(new ErrorResponse(objectError.getDefaultMessage()));
        });
        return validationErrorsFormatted;
    }

}
