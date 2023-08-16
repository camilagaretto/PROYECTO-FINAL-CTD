package com.equipo2.Appkademy.rest.dto.request;

import com.equipo2.Appkademy.rest.error.ErrorCodes;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StudentCreateRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 139534939274393244L;

    @Schema(title = "Student name", example = "Juan Martin", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = ErrorCodes.FIRST_NAME_CANNOT_BE_NULL_OR_EMPTY)
    private String firstName;

    @Schema(title = "Student last name", example = "Rodriguez", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = ErrorCodes.LAST_NAME_CANNOT_BE_NULL_OR_EMPTY)
    private String lastName;

    @Schema(title = "email", example = "juan.martin@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = ErrorCodes.EMAIL_CANNOT_BE_NULL_OR_EMPTY)
    private String email;

    @Schema(title = "Student scheduled appointments", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<ScheduledAppointmentCreateRequestDto> scheduledAppointments;

    @Schema(title = "Address", requiredMode = Schema.RequiredMode.REQUIRED)
    @Valid
    @NotNull(message = ErrorCodes.ADDRESS_CANNOT_BE_NULL)
    private AddressCreateRequestDto address;

}
