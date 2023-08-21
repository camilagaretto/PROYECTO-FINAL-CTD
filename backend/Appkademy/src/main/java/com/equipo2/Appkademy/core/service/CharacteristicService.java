package com.equipo2.Appkademy.core.service;

import com.equipo2.Appkademy.core.model.entity.Characteristic;
import com.equipo2.Appkademy.rest.dto.filter.PageableFilter;
import com.equipo2.Appkademy.rest.dto.request.CharacteristicCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.response.CharacteristicSearchResponseDto;

public interface CharacteristicService {

    Characteristic create(CharacteristicCreateRequestDto createRequestDto);

    CharacteristicSearchResponseDto search(PageableFilter filter);
}
