package com.akhazov.clientservice.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Ошибки валидации.
 */
@Getter
@RequiredArgsConstructor
public enum ValidationError implements Error {

    REQUEST_VALIDATION_ERROR("error-20000", "Request validation error", HttpStatus.BAD_REQUEST),
    REQUEST_FIELD_NOT_BE_EMPTY("error-20001", "Request fields must not be empty. Specify at least one value.", HttpStatus.BAD_REQUEST),
    REQUEST_HEADER_SOURCE_NOT_IDENTIFIED("error-20002", "Required header value not identified.", HttpStatus.BAD_REQUEST),

    MISSING_REQUIRED_FIELDS("error-20100", "Missing required fields.", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_MATCH_FORMAT("error-20101", "Email does not match the format.", HttpStatus.BAD_REQUEST),
    PHONE_NOT_MATCH_FORMAT("error-20102", "Phone does not match the format 7ХХХХХХХХХХ.", HttpStatus.BAD_REQUEST),
    PASSPORT_ID_NOT_MATCH_FORMAT("error-20103", "Passport does not match the format ХХХХ ХХХХХХ.", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String description;
    private final HttpStatus httpStatus;

}
