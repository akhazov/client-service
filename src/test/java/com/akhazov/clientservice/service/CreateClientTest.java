package com.akhazov.clientservice.service;

import com.akhazov.clientservice.config.ValidatorConfiguration;
import com.akhazov.clientservice.error.ServiceException;
import com.akhazov.clientservice.mapper.ClientMapperImpl;
import com.akhazov.clientservice.model.dto.CreateClientRequest;
import com.akhazov.clientservice.model.entity.Client;
import com.akhazov.clientservice.repository.ClientRepository;
import com.akhazov.clientservice.service.specification.ClientSpecificationService;
import com.akhazov.clientservice.service.validation.CreateClientRequestValidationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;
import java.util.stream.Stream;

import static com.akhazov.clientservice.error.ValidationError.MISSING_REQUIRED_FIELDS;
import static com.akhazov.clientservice.error.ValidationError.REQUEST_HEADER_SOURCE_NOT_IDENTIFIED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@DisplayName("Тесты метода создания пользователя сервиса ClientService с проверкой валидности запроса.")
@ExtendWith(SpringExtension.class)
@Import(ValidatorConfiguration.class)
@ContextConfiguration(classes = {ClientService.class, ClientMapperImpl.class, ClientSpecificationService.class, CreateClientRequestValidationService.class})
class CreateClientTest {

    @MockBean
    private ClientRepository clientRepository;

    @Autowired
    private ClientService sut;


    @ParameterizedTest
    @MethodSource
    @DisplayName("Создание клиента с корректным телом запроса.")
    void createClient(String source, CreateClientRequest request) {
        doReturn(TestData.getClientEntity()).when(clientRepository).save(any());
        sut.createClient(source, request);
        verify(clientRepository).save(any());
    }


    private static Stream<Arguments> createClient() {
        return Stream.of(
                Arguments.of("mail", TestData.validMailRequest()),
                Arguments.of("mobile", TestData.validMobileRequest()),
                Arguments.of("bank", TestData.validBankRequest()),
                Arguments.of("gosuslugi", TestData.validGosuslugiRequest())
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Создание клиента с невалидным запросом.")
    void createClient_notValidRequest(String source, CreateClientRequest request) {
        ServiceException exception = assertThrows(ServiceException.class, () -> sut.createClient(source, request));
        assertEquals(MISSING_REQUIRED_FIELDS, exception.getError());
    }

    private static Stream<Arguments> createClient_notValidRequest() {
        return Stream.of(
                Arguments.of("mail", TestData.invalidMailRequest()),
                Arguments.of("mobile", TestData.invalidMobileRequest()),
                Arguments.of("bank", TestData.invalidBankRequest()),
                Arguments.of("gosuslugi", TestData.invalidGosuslugiRequest())
        );
    }

    @Test
    @DisplayName("Создание клиента с невалидным значением источника (source).")
    void createClient_notValidSource() {
        ServiceException exception = assertThrows(ServiceException.class, () -> sut.createClient("web", TestData.validMailRequest()));
        assertEquals(REQUEST_HEADER_SOURCE_NOT_IDENTIFIED, exception.getError());
    }

    private static class TestData {
        public static final UUID ID = UUID.fromString("bf59219c-c9ff-4f4a-b307-07e7dbf8b1b1");

        public static CreateClientRequest validMobileRequest() {
            return CreateClientRequest.builder()
                    .phoneNumber("71234567890")
                    .build();
        }

        public static CreateClientRequest invalidMobileRequest() {
            return CreateClientRequest.builder()
                    .bankId(UUID.randomUUID())
                    .firstName("Ivan")
                    .middleName("Ivanovich")
                    .build();
        }

        public static CreateClientRequest validMailRequest() {
            return CreateClientRequest.builder()
                    .firstName("Ivan")
                    .email("ivanov@gmail.com")
                    .build();
        }

        public static CreateClientRequest invalidMailRequest() {
            return CreateClientRequest.builder()
                    .bankId(UUID.randomUUID())
                    .firstName("Ivan")
                    .middleName("Ivanovich")
                    .build();
        }

        public static CreateClientRequest validBankRequest() {
            return CreateClientRequest.builder()
                    .bankId(UUID.randomUUID())
                    .firstName("Ivan")
                    .middleName("Ivanovich")
                    .lastName("Ivanov")
                    .birthdate(LocalDate.of(2000, Month.APRIL, 07))
                    .passportId("1111 111111")
                    .build();
        }

        public static CreateClientRequest invalidBankRequest() {
            return CreateClientRequest.builder()
                    .bankId(UUID.randomUUID())
                    .firstName("Ivan")
                    .middleName("Ivanovich")
                    .build();
        }

        public static CreateClientRequest validGosuslugiRequest() {
            return CreateClientRequest.builder()
                    .bankId(UUID.randomUUID())
                    .firstName("Ivan")
                    .middleName("Ivanovich")
                    .lastName("Ivanov")
                    .birthdate(LocalDate.of(2000, Month.APRIL, 07))
                    .birthplace("Moscow")
                    .passportId("1111 111111")
                    .phoneNumber("71234567890")
                    .registrationAddress("Moscow")
                    .build();
        }

        public static CreateClientRequest invalidGosuslugiRequest() {
            return CreateClientRequest.builder()
                    .firstName("Ivan")
                    .middleName("Ivanovich")
                    .phoneNumber("71234567890")
                    .build();
        }

        public static Client getClientEntity() {
            Client client = new Client();
            client.setId(ID);
            return client;
        }

    }

}