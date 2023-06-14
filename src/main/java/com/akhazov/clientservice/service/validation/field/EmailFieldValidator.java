package com.akhazov.clientservice.service.validation.field;

import com.akhazov.clientservice.error.ServiceException;
import com.akhazov.clientservice.model.dto.CreateClientRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

import static com.akhazov.clientservice.error.ValidationError.EMAIL_NOT_MATCH_FORMAT;

@Component
public class EmailFieldValidator implements FieldValidator<CreateClientRequest> {

    private static final Pattern emailPattern = Pattern.compile("^(.+)@(\\S+)$");

    @Override
    public void validate(CreateClientRequest request) {
        if (StringUtils.isNotBlank(request.email())
                && !emailPattern.matcher(request.email()).matches()) {
            throw new ServiceException(EMAIL_NOT_MATCH_FORMAT);
        }
    }

}
