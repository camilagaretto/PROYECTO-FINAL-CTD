package com.equipo2.Appkademy.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressCreateRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -73129671520599264L;

    private String country;
    private String province;
    private String city;
    private String streetName;
    private String streetNumber;
    private String floorApt;

}
