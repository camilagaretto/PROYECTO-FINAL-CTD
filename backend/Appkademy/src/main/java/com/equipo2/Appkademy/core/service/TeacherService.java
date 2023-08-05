package com.equipo2.Appkademy.core.service;

import com.equipo2.Appkademy.core.model.entity.Teacher;
import com.equipo2.Appkademy.rest.dto.request.TeacherCreateRequestDto;

public interface TeacherService {

    Teacher getById(Long id);

    Teacher save(TeacherCreateRequestDto createRequestDto);

}
