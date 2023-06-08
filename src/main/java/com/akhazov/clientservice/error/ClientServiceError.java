package com.akhazov.clientservice.error;

import org.springframework.http.HttpStatus;

/**
 * Ошибки сервиса клиентов.
 */
public enum ClientServiceError implements Error {
    CLIENT_NOT_FOUND("error-00001", "Client with id [%s] not found", HttpStatus.NOT_FOUND);

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
