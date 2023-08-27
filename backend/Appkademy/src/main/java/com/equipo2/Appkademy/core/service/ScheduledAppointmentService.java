package com.equipo2.Appkademy.core.service;

import com.equipo2.Appkademy.rest.dto.request.ScheduledAppointmentCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.response.ScheduledAppointmentResponseDto;

public interface ScheduledAppointmentService {

    ScheduledAppointmentResponseDto save(ScheduledAppointmentCreateRequestDto createRequestDto);

    void delete(Long id);
}
