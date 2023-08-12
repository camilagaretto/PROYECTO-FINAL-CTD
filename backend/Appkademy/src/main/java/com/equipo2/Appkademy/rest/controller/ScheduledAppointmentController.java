package com.equipo2.Appkademy.rest.controller;

import com.equipo2.Appkademy.core.mapper.AppkademyMapper;
import com.equipo2.Appkademy.core.model.entity.ScheduledAppointment;
import com.equipo2.Appkademy.core.service.ScheduledAppointmentService;
import com.equipo2.Appkademy.rest.dto.request.ScheduledAppointmentCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.response.ScheduledAppointmentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/v1/categories/1/appointments/")
public class ScheduledAppointmentController implements IScheduledAppointmentController {

    @Autowired
    private AppkademyMapper mapper;

    @Autowired
    private ScheduledAppointmentService scheduledAppointmentService;

    @PostMapping
    public ResponseEntity<ScheduledAppointmentResponseDto> create(@RequestBody ScheduledAppointmentCreateRequestDto
                                                                              createRequestDto){
        ScheduledAppointment appointment = scheduledAppointmentService.save(createRequestDto);
        return new ResponseEntity<ScheduledAppointmentResponseDto>(mapper
                .scheduledAppointmenttoToScheduledAppointmentResponseDto(appointment), HttpStatus.CREATED);
    }

}
