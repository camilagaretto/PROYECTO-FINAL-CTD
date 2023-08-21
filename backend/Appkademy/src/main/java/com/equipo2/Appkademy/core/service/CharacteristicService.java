package com.equipo2.Appkademy.core.service;

import com.equipo2.Appkademy.core.model.entity.Characteristic;
import com.equipo2.Appkademy.rest.dto.request.CharacteristicCreateRequestDto;

public interface CharacteristicService {

    Characteristic create(CharacteristicCreateRequestDto createRequestDto);

}
