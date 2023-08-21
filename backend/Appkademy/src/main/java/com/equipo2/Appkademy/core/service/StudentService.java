package com.equipo2.Appkademy.core.service;

import com.equipo2.Appkademy.core.model.entity.Student;
import com.equipo2.Appkademy.rest.dto.request.StudentCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.request.StudentUpdateRequestDto;

public interface StudentService {

    Student save(StudentCreateRequestDto createRequestDto);

    Student getById(Long id);

    Student update(Long id, StudentUpdateRequestDto updateRequestDto);
}
