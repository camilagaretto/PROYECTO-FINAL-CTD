package com.equipo2.Appkademy.rest.controller;

import com.equipo2.Appkademy.core.mapper.AppkademyMapper;
import com.equipo2.Appkademy.core.service.CharacteristicService;
import com.equipo2.Appkademy.rest.dto.filter.PageableFilter;
import com.equipo2.Appkademy.rest.dto.request.CharacteristicRequestDto;
import com.equipo2.Appkademy.rest.dto.response.CharacteristicResponseDto;
import com.equipo2.Appkademy.rest.dto.response.CharacteristicSearchResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.equipo2.Appkademy.core.security.model.PermissionConstants.*;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/v1/categories/1/providers/characteristics")
public class CharacteristicController implements ICharacteristicController {

    @Autowired
    private CharacteristicService characteristicService;

    @Autowired
    private AppkademyMapper mapper;

    @Override
    @PostMapping
    @PreAuthorize("hasAuthority('" + CHARACTERISTIC_CREATE + "')")
    public ResponseEntity<CharacteristicResponseDto> create(@RequestBody CharacteristicRequestDto createRequestDto){
        CharacteristicResponseDto responseDto = characteristicService.create(createRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Override
    @PostMapping("/search")
    @PreAuthorize("hasAuthority('" + CHARACTERISTIC_READ + "')")
    public ResponseEntity<CharacteristicSearchResponseDto> search(@RequestBody PageableFilter filter){
        CharacteristicSearchResponseDto searchResponse = characteristicService.search(filter);
        return ResponseEntity.ok(searchResponse);
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('" + CHARACTERISTIC_UPDATE + "')")
    public ResponseEntity<CharacteristicResponseDto> update(@PathVariable Long id, @RequestBody @Valid CharacteristicRequestDto
            updateRequestDto){
        CharacteristicResponseDto responseDto = characteristicService.update(id, updateRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('" + CHARACTERISTIC_DELETE + "')")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        characteristicService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
