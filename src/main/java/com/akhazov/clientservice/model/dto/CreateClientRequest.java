package com.akhazov.clientservice.model.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record CreateClientRequest(
        UUID bankId,
        String firstName,
        String middleName,
        String lastName,
        LocalDate birthdate,
        String passportId,
        String birthplace,
        String phoneNumber,
        String email,
        String registrationAddress,
        String residentialAddress
) {
}
