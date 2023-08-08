package com.equipo2.Appkademy.rest.dto.request;

import com.equipo2.Appkademy.rest.error.ErrorCodes;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = ErrorCodes.COUNTRY_CANNOT_BE_NULL_OR_EMPTY)
    private String country;

    @NotBlank(message = ErrorCodes.PROVINCE_CANNOT_BE_NULL_OR_EMPTY)
    private String province;

    @NotBlank(message = ErrorCodes.CITY_CANNOT_BE_NULL_OR_EMPTY)
    private String city;

    @NotBlank(message = ErrorCodes.STREET_NAME_CANNOT_BE_NULL_OR_EMPTY)
    private String streetName;

    @NotBlank(message = ErrorCodes.STREET_NUMBER_CANNOT_BE_NULL_OR_EMPTY)
    private String streetNumber;

    private String floorApt;

}
