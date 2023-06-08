package com.akhazov.clientservice.controller.v1;

import com.akhazov.clientservice.model.dto.CreateClientRequest;
import com.akhazov.clientservice.model.dto.FindClientRequest;
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

    @GetMapping("/{id}")
    @Operation(description = "Получение клиента по идентификатору.")
    public String getClientById(@PathVariable UUID id) {
        return "getClientById";
    }

    @PostMapping
    @Operation(description = "Создание клиента.")
    public String createClient(@RequestBody CreateClientRequest request) {
        return "createClient";
    }

    @PostMapping("/find")
    @Operation(description = "Поиск клиента.")
    public String find(@RequestBody FindClientRequest request) {
        return "findClient";
    }

}

