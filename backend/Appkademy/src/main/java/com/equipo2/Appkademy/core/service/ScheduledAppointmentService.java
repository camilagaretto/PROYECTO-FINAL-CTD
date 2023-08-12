package com.equipo2.Appkademy.core.service;

import com.equipo2.Appkademy.core.model.entity.ScheduledAppointment;
import com.equipo2.Appkademy.rest.dto.request.ScheduledAppointmentCreateRequestDto;

public interface ScheduledAppointmentService {

    ScheduledAppointment save(ScheduledAppointmentCreateRequestDto createRequestDto);

}
