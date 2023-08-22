package com.equipo2.Appkademy.core.service;

import com.equipo2.Appkademy.core.model.entity.Student;
import com.equipo2.Appkademy.rest.dto.filter.PageableFilter;
import com.equipo2.Appkademy.rest.dto.request.StudentCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.request.StudentUpdateRequestDto;
import com.equipo2.Appkademy.rest.dto.response.StudentSearchResponseDto;

public interface StudentService {

    Student save(StudentCreateRequestDto createRequestDto);

    Student getById(Long id);

    Student update(Long id, StudentUpdateRequestDto updateRequestDto);

    StudentSearchResponseDto search(PageableFilter filter);
}
