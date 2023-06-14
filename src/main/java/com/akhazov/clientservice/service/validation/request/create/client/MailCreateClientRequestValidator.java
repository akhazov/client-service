package com.akhazov.clientservice.service.validation.request.create.client;

import com.akhazov.clientservice.error.ServiceException;
import com.akhazov.clientservice.model.dto.CreateClientRequest;
import com.akhazov.clientservice.service.validation.request.RequestValidator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import static com.akhazov.clientservice.error.ValidationError.MISSING_REQUIRED_FIELDS;

@Component("mail")
@RequiredArgsConstructor
public class MailCreateClientRequestValidator implements RequestValidator<CreateClientRequest> {

    @Override
    public void validate(CreateClientRequest request) {
        if (StringUtils.isAnyBlank(
                request.firstName(),
                request.email()
        )) {
            throw new ServiceException(MISSING_REQUIRED_FIELDS);
        }
    }

}
