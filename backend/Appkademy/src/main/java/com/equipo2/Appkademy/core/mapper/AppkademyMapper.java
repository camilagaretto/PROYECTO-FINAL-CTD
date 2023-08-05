package com.equipo2.Appkademy.core.mapper;

import com.equipo2.Appkademy.core.model.entity.Teacher;
import com.equipo2.Appkademy.rest.dto.response.TeacherResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppkademyMapper {

    TeacherResponseDto teacherToTeacherResponseDto(Teacher teacher);

}
