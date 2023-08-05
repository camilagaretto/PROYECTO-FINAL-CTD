package com.equipo2.Appkademy.rest.error;

import java.io.Serial;

public class NotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1536753108201947509L;

    public NotFoundException(String message) {
        super(message);
    }

}
