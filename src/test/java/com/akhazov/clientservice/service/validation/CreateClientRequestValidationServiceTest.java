package com.akhazov.clientservice.service.validation;

import com.akhazov.clientservice.config.ValidatorConfiguration;
import com.akhazov.clientservice.error.ServiceException;
import com.akhazov.clientservice.error.ValidationError;
import com.akhazov.clientservice.model.dto.CreateClientRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

import static com.akhazov.clientservice.error.ValidationError.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@Import(ValidatorConfiguration.class)
@ContextConfiguration(classes = CreateClientRequestValidationService.class)
class CreateClientRequestValidationServiceTest {

    @Autowired
    private CreateClientRequestValidationService sut;

    @ParameterizedTest
    @MethodSource
    @DisplayName("Тест ошибки при неверном формате полей.")
    void validate(String source, CreateClientRequest request, ValidationError error) {
        ServiceException exception = assertThrows(ServiceException.class, () -> sut.validate(source, request));
        assertEquals(error, exception.getError());
    }

    private static Stream<Arguments> validate() {
        return Stream.of(
                Arguments.of("mobile", TestData.invalidPassportIdFormat(), PASSPORT_ID_NOT_MATCH_FORMAT),
                Arguments.of("mobile", TestData.invalidPhoneFormat(), PHONE_NOT_MATCH_FORMAT),
                Arguments.of("mobile", TestData.invalidEmailFormat(), EMAIL_NOT_MATCH_FORMAT)
        );
    }

    private static class TestData {

        public static CreateClientRequest invalidPassportIdFormat() {
            return CreateClientRequest.builder()
                    .passportId("1111111111")
                    .phoneNumber("71234567890")
                    .build();
        }

        public static CreateClientRequest invalidPhoneFormat() {
            return CreateClientRequest.builder()
                    .phoneNumber("+71234567890")
                    .build();
        }

        public static CreateClientRequest invalidEmailFormat() {
            return CreateClientRequest.builder()
                    .phoneNumber("71234567890")
                    .email("ivanovgmail.com")
                    .build();
        }

    }

}