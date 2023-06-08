package com.akhazov.clientservice.model.dto;

public record FindClientRequest(
        String firstName,
        String middleName,
        String lastName,
        String phoneNumber,
        String email
) {
}
