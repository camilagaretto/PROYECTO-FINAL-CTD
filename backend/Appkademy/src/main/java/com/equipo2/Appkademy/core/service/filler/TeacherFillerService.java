package com.equipo2.Appkademy.core.service.filler;

import com.equipo2.Appkademy.core.model.entity.Teacher;
import com.equipo2.Appkademy.rest.dto.request.TeacherPatchRequestDto;

public interface TeacherFillerService {

    Teacher fill(Teacher teacher, TeacherPatchRequestDto patchRequestDto);

}
