package com.akhazov.clientservice.error;

public class ServiceException extends RuntimeException {
    private final Error error;

    public ServiceException(Error error, String... id) {
        super(error.getDescription().formatted(id));
        this.error = error;
    }

    public ServiceException(Error error) {
        super(error.getDescription());
        this.error = error;
    }

    public Error getError() {
        return error;
    }

}
