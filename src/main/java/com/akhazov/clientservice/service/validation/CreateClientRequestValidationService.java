package com.akhazov.clientservice.service.validation;

import com.akhazov.clientservice.error.ServiceException;
import com.akhazov.clientservice.model.dto.CreateClientRequest;
import com.akhazov.clientservice.service.validation.field.FieldValidator;
import com.akhazov.clientservice.service.validation.request.RequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.akhazov.clientservice.error.ValidationError.REQUEST_HEADER_SOURCE_NOT_IDENTIFIED;

@Service
@RequiredArgsConstructor
public class CreateClientRequestValidationService implements SourceValidator<String, CreateClientRequest> {

    private final Map<String, RequestValidator<CreateClientRequest>> sourceValidators;
    private final List<FieldValidator<CreateClientRequest>> fieldValidators;

    public void validate(String source, CreateClientRequest request) {
        if (sourceValidators.get(source) == null) throw new ServiceException(REQUEST_HEADER_SOURCE_NOT_IDENTIFIED);
        sourceValidators.get(source).validate(request);
        fieldValidators.forEach(it -> it.validate(request));
    }

}
