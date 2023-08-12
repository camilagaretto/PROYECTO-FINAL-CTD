package com.equipo2.Appkademy.rest.controller;

import com.equipo2.Appkademy.rest.dto.request.ScheduledAppointmentCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.response.ScheduledAppointmentResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Scheduled Appointment")
public interface IScheduledAppointmentController {

    @Operation(summary = "Create a Scheduled Appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the appointment",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ScheduledAppointmentResponseDto.class)) }),
            @ApiResponse(responseCode = "409", description = "Conflict",
                    content = @Content) })
    ResponseEntity<ScheduledAppointmentResponseDto> create(@RequestBody ScheduledAppointmentCreateRequestDto
                                                                   createRequestDto);
}
