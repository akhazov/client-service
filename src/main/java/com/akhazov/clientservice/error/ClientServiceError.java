package com.akhazov.clientservice.error;

import org.springframework.http.HttpStatus;

/**
 * Ошибки сервиса клиентов.
 */
public enum ClientServiceError implements Error {
    CLIENT_NOT_FOUND("error-00001", "Client with id [%s] not found", HttpStatus.NOT_FOUND),

    REQUEST_VALIDATION_ERROR("error-10000", "Request validation error", HttpStatus.BAD_REQUEST),
    REQUEST_FIELD_NOT_BE_EMPTY("error-10001", "Request fields must not be empty. Specify at least one value.", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String description;
    private final HttpStatus httpStatus;

    ClientServiceError(String code, String description, HttpStatus httpStatus) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public HttpStatus getStatus() {
        return httpStatus;
    }

}
