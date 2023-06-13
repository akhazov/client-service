package com.akhazov.clientservice.service.validation.field;

import com.akhazov.clientservice.error.ServiceException;
import com.akhazov.clientservice.model.dto.CreateClientRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

import static com.akhazov.clientservice.error.ValidationError.PHONE_NOT_MATCH_FORMAT;

@Component
public class PhoneFieldValidator implements FieldValidator<CreateClientRequest> {
    private static final Pattern phonePattern = Pattern.compile("^\\d{10}$");

    @Override
    public void validate(CreateClientRequest request) {
        if (StringUtils.isNotBlank(request.phoneNumber())
                && !phonePattern.matcher(request.phoneNumber()).matches()) {
            throw new ServiceException(PHONE_NOT_MATCH_FORMAT);
        }
    }

}
