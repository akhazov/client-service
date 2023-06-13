package com.akhazov.clientservice.service.validation.request.create.client;

import com.akhazov.clientservice.error.ServiceException;
import com.akhazov.clientservice.model.dto.CreateClientRequest;
import com.akhazov.clientservice.service.validation.request.RequestValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import static com.akhazov.clientservice.error.ValidationError.MISSING_REQUIRED_FIELDS;

@Component("bank")
public class BankCreateClientRequestValidator implements RequestValidator<CreateClientRequest> {

    @Override
    public void validate(CreateClientRequest request) {
        if (StringUtils.isAnyBlank(
                request.bankId().toString(),
                request.firstName(),
                request.middleName(),
                request.lastName(),
                request.birthdate().toString(),
                request.passportId()
        )) {
            throw new ServiceException(MISSING_REQUIRED_FIELDS);
        }
    }

}
