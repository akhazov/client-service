package com.akhazov.clientservice.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;

public record FindClientRequest(
        @NotNull String firstName,
        @NotNull String middleName,
        @NotNull String lastName,
        @NotNull String phoneNumber,
        @NotNull String email
) {

    @JsonIgnore
    public boolean isBlank() {
        return StringUtils.isAllBlank(firstName, middleName, lastName, email, phoneNumber);
    }

}
