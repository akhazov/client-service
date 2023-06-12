package com.akhazov.clientservice.controller;

import com.akhazov.clientservice.error.Error;
import com.akhazov.clientservice.error.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    public ResponseEntity<?> serviceErrorHandler(ServiceException exception) {
        Error error = exception.getError();
        ErrorModel errorModel = new ErrorModel(error.getCode(), exception.getMessage());
        return new ResponseEntity<>(errorModel, error.getStatus());
    }

    private record ErrorModel(String code, String message) {
    }

}
