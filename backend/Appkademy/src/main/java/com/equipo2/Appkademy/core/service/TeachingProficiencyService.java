package com.equipo2.Appkademy.core.service;

import com.equipo2.Appkademy.core.model.entity.TeachingSubject;
import com.equipo2.Appkademy.rest.dto.filter.PageableFilter;
import com.equipo2.Appkademy.rest.dto.request.TeachingSubjectDto;
import com.equipo2.Appkademy.rest.dto.response.TeachingSubjectSearchResponseDto;

public interface TeachingProficiencyService {

    TeachingSubject create(TeachingSubjectDto createDto);

    TeachingSubjectSearchResponseDto search(PageableFilter filter);
}
