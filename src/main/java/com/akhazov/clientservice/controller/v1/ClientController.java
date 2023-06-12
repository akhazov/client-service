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
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/clients")
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
    @Operation(description = "Создание клиента.")
    public CreateClientResponse createClient(@RequestBody CreateClientRequest request) {
        return clientService.createClient(request);
    }

    @PostMapping("/find")
    @Operation(description = "Поиск клиента. С возможность пагинации и сортировки.")
    public Page<FoundClient> find(@Valid @RequestBody FindClientRequest request, @ParameterObject Pageable pageable) {
        return clientService.findClients(request, pageable);
    }

}

