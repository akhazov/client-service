package com.akhazov.clientservice.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.apache.commons.lang3.StringUtils;

@Builder
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
