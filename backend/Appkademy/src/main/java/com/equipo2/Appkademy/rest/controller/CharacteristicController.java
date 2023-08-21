package com.equipo2.Appkademy.rest.controller;

import com.equipo2.Appkademy.core.mapper.AppkademyMapper;
import com.equipo2.Appkademy.core.model.entity.Characteristic;
import com.equipo2.Appkademy.core.service.CharacteristicService;
import com.equipo2.Appkademy.rest.dto.filter.PageableFilter;
import com.equipo2.Appkademy.rest.dto.request.CharacteristicCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.response.CharacteristicResponseDto;
import com.equipo2.Appkademy.rest.dto.response.CharacteristicSearchResponseDto;
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
@RequestMapping(path = "/v1/categories/1/providers/characteristics")
public class CharacteristicController implements ICharacteristicController {

    @Autowired
    private CharacteristicService characteristicService;

    @Autowired
    private AppkademyMapper mapper;

    @PostMapping
    @Override
    public ResponseEntity<CharacteristicResponseDto> create(@RequestBody CharacteristicCreateRequestDto createRequestDto){
        Characteristic entity = characteristicService.create(createRequestDto);
        CharacteristicResponseDto responseDto = mapper.characteristicToCharacteristicResponseDto(entity);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/search")
    @Override
    public ResponseEntity<CharacteristicSearchResponseDto> search(@RequestBody PageableFilter filter){
        CharacteristicSearchResponseDto searchResponse = characteristicService.search(filter);
        return ResponseEntity.ok(searchResponse);
    }

}
