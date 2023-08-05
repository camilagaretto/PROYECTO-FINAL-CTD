package com.equipo2.Appkademy.rest.dto.response;

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
public class ScheduledAppointmentResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 7162916264900472187L;

    private Long id;
    private LocalDateTime startsOn;
    private LocalDateTime endsOn;
    private Long providerId;
    private Long customerId;

}
