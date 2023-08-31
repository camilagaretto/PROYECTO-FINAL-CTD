package com.equipo2.Appkademy.core.service;

import com.equipo2.Appkademy.rest.dto.filter.TeacherFilterDto;
import com.equipo2.Appkademy.rest.dto.request.TeacherCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.request.TeacherUpdateRequestDto;
import com.equipo2.Appkademy.rest.dto.response.TeacherResponseDto;
import com.equipo2.Appkademy.rest.dto.response.TeacherSearchResponseDto;

public interface TeacherService {

    TeacherResponseDto getById(Long id);

    TeacherResponseDto save(TeacherCreateRequestDto createRequestDto);

    TeacherSearchResponseDto search(TeacherFilterDto filter);

    //Teacher patch(Long id, TeacherPatchRequestDto patchRequestDto);

    void delete(Long id);

    TeacherResponseDto update(Long id, TeacherUpdateRequestDto updateRequestDto);
}
