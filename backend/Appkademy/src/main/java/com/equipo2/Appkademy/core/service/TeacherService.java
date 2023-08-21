package com.equipo2.Appkademy.core.service;

import com.equipo2.Appkademy.core.model.entity.Teacher;
import com.equipo2.Appkademy.rest.dto.filter.TeacherFilterDto;
import com.equipo2.Appkademy.rest.dto.request.TeacherCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.request.TeacherUpdateRequestDto;
import com.equipo2.Appkademy.rest.dto.response.TeacherSearchResponseDto;

public interface TeacherService {

    Teacher getById(Long id);

    Teacher save(TeacherCreateRequestDto createRequestDto);

    TeacherSearchResponseDto search(TeacherFilterDto filter);

    //Teacher patch(Long id, TeacherPatchRequestDto patchRequestDto);

    void delete(Long id);

    Teacher update(Long id, TeacherUpdateRequestDto updateRequestDto);
}
