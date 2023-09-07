package com.equipo2.Appkademy.rest.controller.documentation;

import com.equipo2.Appkademy.rest.dto.filter.PageableFilter;
import com.equipo2.Appkademy.rest.dto.request.StudentCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.request.StudentPatchRequestDto;
import com.equipo2.Appkademy.rest.dto.request.StudentUpdateRequestDto;
import com.equipo2.Appkademy.rest.dto.response.StudentResponseDto;
import com.equipo2.Appkademy.rest.dto.response.StudentSearchResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Student")
public interface IStudentController {


    @Operation(summary = "Get a Student by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Student",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content) })
    ResponseEntity<StudentResponseDto> getById(@PathVariable Long id);

    @Operation(summary = "Create a Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the Student",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentResponseDto.class)) }),
            @ApiResponse(responseCode = "409", description = "Conflict",
                    content = @Content) })
    ResponseEntity<StudentResponseDto> create(@RequestBody StudentCreateRequestDto createRequestDto);

    @Operation(summary = "Update a Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated Student",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentResponseDto.class)) }),
            @ApiResponse(responseCode = "409", description = "Conflict",
                    content = @Content) })
    ResponseEntity<StudentResponseDto> update(Long id, @RequestBody StudentUpdateRequestDto updateRequestDto);

    @Operation(summary = "Paginated search of Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully searched Student",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentSearchResponseDto.class)) })})
    ResponseEntity<StudentSearchResponseDto> search(@RequestBody PageableFilter filter);

    @Operation(summary = "Patch a Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patched Student",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentResponseDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content) })
    ResponseEntity<StudentResponseDto> patch(@PathVariable Long id, StudentPatchRequestDto patchRequestDto);
}
