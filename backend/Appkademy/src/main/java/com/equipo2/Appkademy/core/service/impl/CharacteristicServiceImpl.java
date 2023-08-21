package com.equipo2.Appkademy.core.service.impl;

import com.equipo2.Appkademy.core.model.entity.Characteristic;
import com.equipo2.Appkademy.core.security.model.repository.CharacteristicRespository;
import com.equipo2.Appkademy.core.service.CharacteristicService;
import com.equipo2.Appkademy.rest.dto.request.CharacteristicCreateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CharacteristicServiceImpl implements CharacteristicService {

    @Autowired
    private CharacteristicRespository characteristicRespository;

    @Override
    public Characteristic create(CharacteristicCreateRequestDto createRequestDto) {

        Optional<Characteristic> characteristicOptional = characteristicRespository.findByName(createRequestDto.getName());

        if(characteristicOptional.isPresent()){
            return characteristicOptional.get();
        }

        Characteristic entity = new Characteristic();
        entity.setName(createRequestDto.getName());
        entity.setIcon(createRequestDto.getIcon());

        return characteristicRespository.save(entity);
    }
}
