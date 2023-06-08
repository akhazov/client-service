package com.akhazov.clientservice.controller.v1;

import com.akhazov.clientservice.model.dto.CreateClientRequest;
import com.akhazov.clientservice.model.dto.CreateClientResponse;
import com.akhazov.clientservice.model.dto.FindClientRequest;
import com.akhazov.clientservice.model.dto.GetClientResponse;
import com.akhazov.clientservice.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
    @Operation(description = "Поиск клиента.")
    public String find(@RequestBody FindClientRequest request) {
        return "findClient";
    }

}

