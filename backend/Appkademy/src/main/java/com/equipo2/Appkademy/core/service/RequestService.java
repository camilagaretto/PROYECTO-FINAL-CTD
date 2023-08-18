package com.equipo2.Appkademy.core.service;

import com.equipo2.Appkademy.core.model.entity.TeacherSignupRequest;
import com.equipo2.Appkademy.rest.dto.request.TeacherSignupRequestCreateDto;

public interface RequestService {

    TeacherSignupRequest createSignupRequest(TeacherSignupRequestCreateDto signupRequest);

    TeacherSignupRequest getSignUpRequestById(Long id);

}
