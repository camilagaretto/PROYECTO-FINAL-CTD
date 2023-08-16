package com.equipo2.Appkademy.rest.dto.request;

import com.equipo2.Appkademy.rest.error.ErrorCodes;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduledAppointmentCreateRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 271769920953834445L;

    @NotNull(message = ErrorCodes.STARTS_ON_CANNOT_BE_NULL)
    private LocalDateTime startsOn;

    @NotNull(message = ErrorCodes.ENDS_ON_CANNOT_BE_NULL)
    private LocalDateTime endsOn;

    @NotNull(message = ErrorCodes.PROVIDER_ID_CANNOT_BE_NULL)
    private Long teacherId;

    @NotNull(message = ErrorCodes.CUSTOMER_ID_CANNOT_BE_NULL)
    private Long studentId;

}
