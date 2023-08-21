package com.equipo2.Appkademy.core.service;

import com.equipo2.Appkademy.core.model.entity.Characteristic;
import com.equipo2.Appkademy.rest.dto.filter.PageableFilter;
import com.equipo2.Appkademy.rest.dto.request.CharacteristicRequestDto;
import com.equipo2.Appkademy.rest.dto.response.CharacteristicSearchResponseDto;

public interface CharacteristicService {

    Characteristic create(CharacteristicRequestDto createRequestDto);

    CharacteristicSearchResponseDto search(PageableFilter filter);

    void delete(Long id);

    Characteristic update(Long id, CharacteristicRequestDto updateRequestDto);
}
