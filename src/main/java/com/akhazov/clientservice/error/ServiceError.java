package com.akhazov.clientservice.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Ошибки сервиса.
 */
@Getter
@RequiredArgsConstructor
public enum ServiceError implements Error {

    CLIENT_NOT_FOUND("error-00001", "Client with id [%s] not found", HttpStatus.NOT_FOUND);

    private final String code;
    private final String description;
    private final HttpStatus httpStatus;

}
