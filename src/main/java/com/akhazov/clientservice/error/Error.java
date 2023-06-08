package com.akhazov.clientservice.error;

import org.springframework.http.HttpStatus;

public interface Error {

    String getCode();

    String getDescription();

    HttpStatus getStatus();

}
