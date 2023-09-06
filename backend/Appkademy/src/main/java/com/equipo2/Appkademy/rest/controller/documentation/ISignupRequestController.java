package com.equipo2.Appkademy.rest.controller.documentation;

import com.equipo2.Appkademy.rest.dto.request.TeacherSignupRequestCreateDto;
import com.equipo2.Appkademy.rest.dto.response.TeacherSignupRequestResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Signup Request")
public interface ISignupRequestController {


    @Operation(summary = "Create a Signup Request for Teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the Signup Request for Teacher",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TeacherSignupRequestResponseDto.class)) }),
            @ApiResponse(responseCode = "409", description = "Conflict",
                    content = @Content) })
    ResponseEntity<TeacherSignupRequestResponseDto> createSignupRequest(@RequestBody @Valid TeacherSignupRequestCreateDto signupRequestDto);


    @Operation(summary = "Get a Signup Request by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Signup Request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TeacherSignupRequestResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Signup Request not found",
                    content = @Content) })
    ResponseEntity<TeacherSignupRequestResponseDto> getSignupRequestById(@PathVariable Long id);
}
