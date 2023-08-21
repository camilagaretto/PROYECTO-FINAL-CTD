package com.equipo2.Appkademy.core.service.impl;

import com.equipo2.Appkademy.core.mapper.AppkademyMapper;
import com.equipo2.Appkademy.core.model.entity.Characteristic;
import com.equipo2.Appkademy.core.security.model.repository.CharacteristicRespository;
import com.equipo2.Appkademy.core.service.CharacteristicService;
import com.equipo2.Appkademy.rest.dto.filter.PageableFilter;
import com.equipo2.Appkademy.rest.dto.request.CharacteristicCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.response.CharacteristicSearchResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CharacteristicServiceImpl implements CharacteristicService {

    @Autowired
    private CharacteristicRespository characteristicRespository;

    @Autowired
    private AppkademyMapper mapper;

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

    @Override
    public CharacteristicSearchResponseDto search(PageableFilter filter) {
        if(Objects.isNull(filter.getPageNumber())){
            filter.setPageNumber(1);
        } else {
            filter.setPageNumber(filter.getPageNumber());
        }

        if(Objects.isNull(filter.getPageSize())){
            filter.setPageSize(10);
        }

        PageRequest pageable = PageRequest.of(filter.getPageNumber()-1, filter.getPageSize());

        Page<Characteristic> resultPage = characteristicRespository.findAll(pageable);
        List<Characteristic> resultList = resultPage.getContent();

        CharacteristicSearchResponseDto searchResponseDto = new CharacteristicSearchResponseDto();
        searchResponseDto.setTotalItemsFound(resultPage.getTotalElements());
        searchResponseDto.setTotalPagesFound(resultPage.getTotalPages());
        searchResponseDto.setPageSizeSelected(filter.getPageSize());
        searchResponseDto.setPageNumberSelected(filter.getPageNumber());
        searchResponseDto.setSearchResults(mapper.characteristicListToCharacteristicResponseDtoList(resultList));

        return searchResponseDto;
    }
}
