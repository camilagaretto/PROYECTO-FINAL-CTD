package com.equipo2.Appkademy.rest.dto.request;

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

    private LocalDateTime startsOn;
    private LocalDateTime endsOn;
    private Long providerId;
    private Long customerId;

}
