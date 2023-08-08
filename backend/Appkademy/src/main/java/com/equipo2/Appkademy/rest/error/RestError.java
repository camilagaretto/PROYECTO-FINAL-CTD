package com.equipo2.Appkademy.rest.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestError {

    private String httpCode;
    private String codeMessage;
    private String cause;

}
