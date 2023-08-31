package com.equipo2.Appkademy.core.service;

import com.equipo2.Appkademy.rest.dto.filter.PageableFilter;
import com.equipo2.Appkademy.rest.dto.request.StudentCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.request.StudentUpdateRequestDto;
import com.equipo2.Appkademy.rest.dto.response.StudentResponseDto;
import com.equipo2.Appkademy.rest.dto.response.StudentSearchResponseDto;

public interface StudentService {

    StudentResponseDto save(StudentCreateRequestDto createRequestDto);

    StudentResponseDto getById(Long id);

    StudentResponseDto update(Long id, StudentUpdateRequestDto updateRequestDto);

    StudentSearchResponseDto search(PageableFilter filter);
}
