package com.akhazov.clientservice.controller;

import com.akhazov.clientservice.error.Error;
import com.akhazov.clientservice.error.ServiceException;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

import static com.akhazov.clientservice.error.ValidationError.REQUEST_VALIDATION_ERROR;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorModel> serviceErrorHandler(ServiceException exception) {
        Error error = exception.getError();
        ErrorModel errorModel = new ErrorModel(error.getCode(), exception.getMessage(), null);
        return new ResponseEntity<>(errorModel, error.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorModel> handleValidationExceptions(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getAllErrors()
                .stream()
                .map(error -> ((FieldError) error).getField() + " " + error.getDefaultMessage())
                .toList();

        ErrorModel errorModel = new ErrorModel(REQUEST_VALIDATION_ERROR.getCode(), REQUEST_VALIDATION_ERROR.getDescription(), errors);
        return new ResponseEntity<>(errorModel, REQUEST_VALIDATION_ERROR.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorModel> commonExceptionsHandler(Exception exception) {
        ErrorModel errorModel = new ErrorModel(REQUEST_VALIDATION_ERROR.getCode(), exception.getMessage(), null);
        return new ResponseEntity<>(errorModel, REQUEST_VALIDATION_ERROR.getHttpStatus());
    }

    @JsonInclude(NON_NULL)
    private record ErrorModel(String code, String message, List<String> errors) {
    }

}
