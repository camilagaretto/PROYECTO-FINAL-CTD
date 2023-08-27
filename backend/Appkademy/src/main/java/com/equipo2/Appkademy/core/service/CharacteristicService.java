package com.equipo2.Appkademy.core.service;

import com.equipo2.Appkademy.rest.dto.filter.PageableFilter;
import com.equipo2.Appkademy.rest.dto.request.CharacteristicRequestDto;
import com.equipo2.Appkademy.rest.dto.response.CharacteristicResponseDto;
import com.equipo2.Appkademy.rest.dto.response.CharacteristicSearchResponseDto;

public interface CharacteristicService {

    CharacteristicResponseDto create(CharacteristicRequestDto createRequestDto);

    CharacteristicSearchResponseDto search(PageableFilter filter);

    void delete(Long id);

    CharacteristicResponseDto update(Long id, CharacteristicRequestDto updateRequestDto);
}
