package com.equipo2.Appkademy.rest.dto.request;

import com.equipo2.Appkademy.rest.error.ErrorCodes;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CharacteristicRequestDto {

    @NotBlank(message = ErrorCodes.CHARACTERISTIC_ICON_CANNOT_BE_NULL_OR_EMPTY)
    private String icon;

    @NotBlank(message = ErrorCodes.CHARACTERISTIC_NAME_CANNOT_BE_NULL_OR_EMPTY)
    private String name;

}
