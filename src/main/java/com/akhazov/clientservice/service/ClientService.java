package com.akhazov.clientservice.service;

import com.akhazov.clientservice.error.ServiceException;
import com.akhazov.clientservice.mapper.ClientMapper;
import com.akhazov.clientservice.model.dto.*;
import com.akhazov.clientservice.model.entity.Client;
import com.akhazov.clientservice.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.akhazov.clientservice.error.ClientServiceError.CLIENT_NOT_FOUND;
import static com.akhazov.clientservice.error.ClientServiceError.REQUEST_FIELD_NOT_BE_EMPTY;

@Service
@Transactional
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper mapper;
    private final SpecificationService specificationService;

    public ClientService(ClientRepository clientRepository, ClientMapper mapper, SpecificationService specificationService) {
        this.clientRepository = clientRepository;
        this.mapper = mapper;
        this.specificationService = specificationService;
    }

    /**
     * Метод создания слиента.
     *
     * @param request Данные запроса на создание клиента.
     * @return Ответ с идентификатором созданного клиента.
     */
    public CreateClientResponse createClient(CreateClientRequest request) {
        Client preparedClient = mapper.createRequestToEntity(request);
        Client savedClient = clientRepository.save(preparedClient);
        return new CreateClientResponse(savedClient.getId());
    }

    /**
     * Поиск клиента по идентификатору.
     *
     * @param id Идентификатор в формате uuid.
     * @return Данные найденного клиента.
     * @throws ServiceException, если клиент не найден.
     */
    @Transactional(readOnly = true)
    public GetClientResponse getClientById(UUID id) {
        Client foundClient = clientRepository.findById(id).orElseThrow(() -> new ServiceException(CLIENT_NOT_FOUND, id.toString()));
        return mapper.entityToGetClientResponse(foundClient);
    }

    /**
     * Поиск клиента по параметрам.
     *
     * @param request  Параметры поиска
     * @param pageable Объект с параметрами пагинации.
     * @return Результаты поиска.
     */
    public Page<FoundClient> findClients(FindClientRequest request, Pageable pageable) {
        if (request.isBlank()) throw new ServiceException(REQUEST_FIELD_NOT_BE_EMPTY);
        Specification<Client> searchSpecification = specificationService.getSearchClientSpec(request);
        return clientRepository.findAll(searchSpecification, pageable)
                .map(mapper::entityToFoundClient);
    }

}
