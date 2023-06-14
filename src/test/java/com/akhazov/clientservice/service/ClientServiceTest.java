package com.akhazov.clientservice.service;

import com.akhazov.clientservice.error.ServiceException;
import com.akhazov.clientservice.mapper.ClientMapperImpl;
import com.akhazov.clientservice.model.dto.FindClientRequest;
import com.akhazov.clientservice.model.dto.FoundClient;
import com.akhazov.clientservice.model.dto.GetClientResponse;
import com.akhazov.clientservice.model.entity.Client;
import com.akhazov.clientservice.repository.ClientRepository;
import com.akhazov.clientservice.service.specification.ClientSpecificationService;
import com.akhazov.clientservice.service.validation.CreateClientRequestValidationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.akhazov.clientservice.error.ServiceError.CLIENT_NOT_FOUND;
import static com.akhazov.clientservice.error.ValidationError.REQUEST_FIELD_NOT_BE_EMPTY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@DisplayName("Тесты всех методов сервиса ClientService, кроме создания клиента.")
@ExtendWith(SpringExtension.class)
@MockBeans(@MockBean(CreateClientRequestValidationService.class))
@ContextConfiguration(classes = {ClientService.class, ClientMapperImpl.class, ClientSpecificationService.class})
class ClientServiceTest {

    @MockBean
    private ClientRepository clientRepository;

    @Autowired
    private ClientService sut;

    @Test
    @DisplayName("Тест получения пользователя по идентификатору.")
    void getClientById() {
        doReturn(Optional.of(TestData.getClientEntity())).when(clientRepository).findById(TestData.ID);
        GetClientResponse client = sut.getClientById(TestData.ID);
        assertEquals(TestData.ID, client.id());
    }

    @Test
    @DisplayName("Пользователь по идентификатору не найден.")
    void getClientById_clientNotFound() {
        doReturn(Optional.empty()).when(clientRepository).findById(TestData.ID);
        ServiceException exception = assertThrows(ServiceException.class, () -> sut.getClientById(TestData.ID));
        assertEquals(CLIENT_NOT_FOUND, exception.getError());
    }

    @Test
    @DisplayName("Поиск клиента с пустым запросом.")
    void findClients_isBlankRequest() {
        ServiceException exception = assertThrows(ServiceException.class, () -> sut.findClients(TestData.getBlankFindClientRequest(), Pageable.unpaged()));
        assertEquals(REQUEST_FIELD_NOT_BE_EMPTY, exception.getError());
    }

    @Test
    @DisplayName("Поиск клиента.")
    void findClients() {
        doReturn(TestData.getPageClients()).when(clientRepository).findAll(any(Specification.class), any(Pageable.class));
        Page<FoundClient> clients = sut.findClients(TestData.getFindClientRequest(), Pageable.unpaged());
        assertEquals(1, clients.getTotalElements());
    }

    private static class TestData {
        public static final UUID ID = UUID.fromString("bf59219c-c9ff-4f4a-b307-07e7dbf8b1b1");

        public static FindClientRequest getFindClientRequest() {
            return FindClientRequest.builder()
                    .email("ivanov@gmail.com")
                    .build();
        }

        public static FindClientRequest getBlankFindClientRequest() {
            return FindClientRequest.builder().build();
        }

        public static Client getClientEntity() {
            Client client = new Client();
            client.setId(ID);
            return client;
        }

        public static Page<Client> getPageClients() {
            return new PageImpl<>(List.of(getClientEntity()));
        }

    }

}
