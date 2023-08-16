package com.equipo2.Appkademy.core.service;

import com.equipo2.Appkademy.core.model.entity.Student;
import com.equipo2.Appkademy.rest.dto.request.StudentCreateRequestDto;

public interface StudentService {

    Student save(StudentCreateRequestDto createRequestDto);

    Student getById(Long id);

}
