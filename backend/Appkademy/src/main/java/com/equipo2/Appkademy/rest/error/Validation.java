package com.equipo2.Appkademy.rest.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Validation {

    private String invalidAttribute;
    private String invalidValue;

}
