package com.equipo2.Appkademy.rest.dto.request;

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

    private LocalTime checkIn;
    private LocalTime checkOut;
    private boolean sunday;
    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean saturday;

}
