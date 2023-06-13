package com.akhazov.clientservice.service.validation;

/**
 * Валидатор источника.
 *
 * @param <S> Тип источника.
 * @param <T> Тип запроса, который нужно провалидировать по источнику.
 */
public interface SourceValidator<S, T> {

    void validate(S source, T request);

}
