package com.akhazov.clientservice.model.dto;

import java.util.UUID;

public record CreateClientResponse(
        UUID clientId
) {
}
