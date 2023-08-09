package com.equipo2.Appkademy.rest.controller;

import com.equipo2.Appkademy.rest.dto.filter.TeacherFilterDto;
import com.equipo2.Appkademy.rest.dto.request.TeacherCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.response.TeacherResponseDto;
import com.equipo2.Appkademy.rest.dto.response.TeacherSearchResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Teacher")
public interface ITeacherProviderController {

    @Operation(summary = "Get a Teacher by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Teacher",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TeacherResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Teacher not found",
                    content = @Content) })
    ResponseEntity<TeacherResponseDto> get(@PathVariable Long id);

    @Operation(summary = "Create a Teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the Teacher",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TeacherResponseDto.class)) }),
            @ApiResponse(responseCode = "409", description = "Conflict",
                    content = @Content) })
    ResponseEntity<TeacherResponseDto> create(@Valid @RequestBody TeacherCreateRequestDto TeacherCreateRequestDto);

    @Operation(summary = "Paginated search of Teachers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully searched teachers",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TeacherSearchResponseDto.class)) })})
    ResponseEntity<TeacherSearchResponseDto> search(@ParameterObject TeacherFilterDto filter);

    @Operation(summary = "Delete a Teacher by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Teacher not found",
                    content = @Content) })
    ResponseEntity<Void> delete(@PathVariable Long id);
}
