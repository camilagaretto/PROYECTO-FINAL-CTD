package com.equipo2.Appkademy.rest.controller;

import com.equipo2.Appkademy.rest.dto.filter.PageableFilter;
import com.equipo2.Appkademy.rest.dto.request.CharacteristicCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.response.CharacteristicResponseDto;
import com.equipo2.Appkademy.rest.dto.response.CharacteristicSearchResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Characteristic")
public interface ICharacteristicController {


    @Operation(summary = "Create a Characteristic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created Characteristic",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacteristicResponseDto.class)) }),
            @ApiResponse(responseCode = "409", description = "Conflict",
                    content = @Content) })
    ResponseEntity<CharacteristicResponseDto> create(@RequestBody CharacteristicCreateRequestDto createRequestDto);

    @Operation(summary = "Paginated search of Characteristics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully searched Characteristics",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CharacteristicSearchResponseDto.class)) })})
    ResponseEntity<CharacteristicSearchResponseDto> search(@RequestBody PageableFilter filter);

}
