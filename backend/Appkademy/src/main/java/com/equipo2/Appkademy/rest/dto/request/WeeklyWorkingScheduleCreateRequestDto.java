package com.equipo2.Appkademy.rest.dto.request;

import com.equipo2.Appkademy.rest.error.ErrorCodes;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeeklyWorkingScheduleCreateRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 6398957386001967713L;

    @Schema(title = "Check in time", implementation = String.class, example = "09:00:00.000", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = ErrorCodes.CHECK_IN_CANNOT_BE_NULL)
    private LocalTime checkIn;

    @Schema(title = "Check out time", implementation = String.class, example = "18:00:00.000", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = ErrorCodes.CHECK_OUT_CANNOT_BE_NULL)
    private LocalTime checkOut;

    @Schema(title = "If teacher works on sundays", example = "false", requiredMode = Schema.RequiredMode.REQUIRED)
    private boolean sunday;

    @Schema(title = "If teacher works on mondays", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    private boolean monday;

    @Schema(title = "If teacher works on tuesdays", example = "false", requiredMode = Schema.RequiredMode.REQUIRED)
    private boolean tuesday;

    @Schema(title = "If teacher works on wednesdays", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    private boolean wednesday;

    @Schema(title = "If teacher works on thursdays", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    private boolean thursday;

    @Schema(title = "If teacher works on fridays", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    private boolean friday;

    @Schema(title = "If teacher works on saturdays", example = "false", requiredMode = Schema.RequiredMode.REQUIRED)
    private boolean saturday;

}
