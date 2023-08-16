package com.equipo2.Appkademy.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class TeacherPatchRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -637022249950323536L;

    @Schema(title = "Scheduled appointment")
    private ScheduledAppointmentCreateRequestDto scheduledAppointment;


}
