package com.akhazov.clientservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

public record GetClientResponse(
        @JsonProperty("clientId") UUID id,
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
        String residentialAddress,
        Timestamp createDate,
        Timestamp updateDate,
        Integer version
) {
}
