package com.equipo2.Appkademy.rest.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BadRequestException extends RuntimeException {

    private String invalidAttribute;
    private String invalidValue;

    public BadRequestException(String message, String invalidAttribute, String invalidValue) {
        super(message);
        this.invalidAttribute = invalidAttribute;
        this.invalidValue = invalidValue;
    }
}
