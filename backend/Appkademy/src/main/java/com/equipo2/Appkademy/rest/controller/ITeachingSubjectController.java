package com.equipo2.Appkademy.rest.controller;

import com.equipo2.Appkademy.rest.dto.filter.PageableFilter;
import com.equipo2.Appkademy.rest.dto.request.TeachingSubjectDto;
import com.equipo2.Appkademy.rest.dto.response.TeacherSearchResponseDto;
import com.equipo2.Appkademy.rest.dto.response.TeachingSubjectResponseDto;
import com.equipo2.Appkademy.rest.dto.response.TeachingSubjectSearchResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Category: Teaching Subject")
public interface ITeachingSubjectController {

    @Operation(summary = "Create a Teaching Subject")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created Teaching Subject",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TeachingSubjectResponseDto.class)) }),
            @ApiResponse(responseCode = "409", description = "Conflict",
                    content = @Content) })
    ResponseEntity<TeachingSubjectResponseDto> create(@RequestBody TeachingSubjectDto createDto);

    @Operation(summary = "Paginated search of Teaching Subject")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully searched Teaching Subject",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TeacherSearchResponseDto.class)) })})
    ResponseEntity<TeachingSubjectSearchResponseDto> search(@RequestBody PageableFilter filter);

}
