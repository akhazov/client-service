package com.akhazov.clientservice.controller.v1;

import com.akhazov.clientservice.model.dto.*;
import com.akhazov.clientservice.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/v1/clients", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Client controller", description = "API работы с клиентом.")
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/{id}")
    @Operation(description = "Получение клиента по идентификатору.")
    public GetClientResponse getClientById(@PathVariable UUID id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Создание клиента.")
    public CreateClientResponse createClient(@RequestHeader("x-Source") String source, @RequestBody CreateClientRequest request) {
        return clientService.createClient(source, request);
    }

    @PostMapping("/find")
    @Operation(description = "Поиск клиента. С возможность пагинации и сортировки.")
    public Page<FoundClient> find(@Valid @RequestBody FindClientRequest request, @ParameterObject Pageable pageable) {
        return clientService.findClients(request, pageable);
    }

}

