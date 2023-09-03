package com.equipo2.Appkademy.core.service;

import com.equipo2.Appkademy.rest.dto.filter.PageableFilter;
import com.equipo2.Appkademy.rest.dto.request.TeachingSubjectDto;
import com.equipo2.Appkademy.rest.dto.response.TeachingSubjectResponseDto;
import com.equipo2.Appkademy.rest.dto.response.TeachingSubjectSearchResponseDto;

public interface TeachingProficiencyService {

    TeachingSubjectResponseDto create(TeachingSubjectDto createDto);

    TeachingSubjectSearchResponseDto search(PageableFilter filter);

    void delete(Long id);

}
