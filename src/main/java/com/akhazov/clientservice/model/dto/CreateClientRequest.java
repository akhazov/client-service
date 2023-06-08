package com.akhazov.clientservice.model.dto;

import java.time.LocalDate;
import java.util.UUID;

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
