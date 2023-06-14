package com.akhazov.clientservice.service.validation.request;

/**
 * Валидатор тела запроса.
 *
 * @param <T> Тип запроса.
 */
public interface RequestValidator<T> {

    void validate(T request);

}
