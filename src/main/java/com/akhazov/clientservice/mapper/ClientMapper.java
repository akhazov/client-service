package com.akhazov.clientservice.mapper;

import com.akhazov.clientservice.model.dto.CreateClientRequest;
import com.akhazov.clientservice.model.dto.FoundClient;
import com.akhazov.clientservice.model.dto.GetClientResponse;
import com.akhazov.clientservice.model.entity.Client;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClientMapper {

    @Mapping(target = "id", ignore = true)
    Client createRequestToEntity(CreateClientRequest request);

    GetClientResponse entityToGetClientResponse(Client foundClient);

    FoundClient entityToFoundClient(Client foundClient);

}
