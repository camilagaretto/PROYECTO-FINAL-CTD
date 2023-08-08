package com.equipo2.Appkademy.rest.dto.request;

import com.equipo2.Appkademy.rest.error.ErrorCodes;
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

    @NotNull(message = ErrorCodes.CHECK_IN_CANNOT_BE_NULL)
    private LocalTime checkIn;

    @NotNull(message = ErrorCodes.CHECK_OUT_CANNOT_BE_NULL)
    private LocalTime checkOut;

    private boolean sunday;
    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean saturday;

}
