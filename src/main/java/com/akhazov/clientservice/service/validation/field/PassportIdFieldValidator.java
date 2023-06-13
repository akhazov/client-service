package com.akhazov.clientservice.service.validation.field;

import com.akhazov.clientservice.error.ServiceException;
import com.akhazov.clientservice.model.dto.CreateClientRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

import static com.akhazov.clientservice.error.ValidationError.PASSPORT_ID_NOT_MATCH_FORMAT;

@Component
public class PassportIdFieldValidator implements FieldValidator<CreateClientRequest> {
    private static final Pattern idPattern = Pattern.compile("^(d{4}) (d{6})$");

    @Override
    public void validate(CreateClientRequest request) {
        if (StringUtils.isNotBlank(request.passportId())
                && !idPattern.matcher(request.passportId()).matches()) {
            throw new ServiceException(PASSPORT_ID_NOT_MATCH_FORMAT);
        }
    }

}
