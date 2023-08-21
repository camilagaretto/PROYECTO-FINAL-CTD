package com.equipo2.Appkademy.rest.dto.request;

import com.equipo2.Appkademy.rest.error.ErrorCodes;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class AddressRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -73129671520599264L;

    @Schema(title = "Country", example = "ARGENTINA", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = ErrorCodes.COUNTRY_CANNOT_BE_NULL_OR_EMPTY)
    private String country;

    @Schema(title = "Province", example = "CIUDAD_DE_BUENOS_AIRES", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = ErrorCodes.PROVINCE_CANNOT_BE_NULL_OR_EMPTY)
    private String province;

    @Schema(title = "City", example = "CIUDAD_DE_BUENOS_AIRES", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = ErrorCodes.CITY_CANNOT_BE_NULL_OR_EMPTY)
    private String city;

    @Schema(title = "Street name", example = "Av. Cabildo 2023", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = ErrorCodes.STREET_NAME_CANNOT_BE_NULL_OR_EMPTY)
    private String streetName;

    @Schema(title = "Street number", example = "4450", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = ErrorCodes.STREET_NUMBER_CANNOT_BE_NULL_OR_EMPTY)
    private String streetNumber;

    @Schema(title = "Floor/apartment", example = "4A", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String floorApt;

}
