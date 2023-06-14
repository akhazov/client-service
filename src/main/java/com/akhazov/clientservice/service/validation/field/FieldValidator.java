package com.akhazov.clientservice.service.validation.field;

public interface FieldValidator<T> {

    void validate(T var);

}
