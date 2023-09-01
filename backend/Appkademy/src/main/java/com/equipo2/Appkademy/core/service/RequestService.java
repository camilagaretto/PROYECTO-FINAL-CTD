package com.equipo2.Appkademy.core.service;

import com.equipo2.Appkademy.rest.dto.request.TeacherSignupRequestCreateDto;
import com.equipo2.Appkademy.rest.dto.response.TeacherSignupRequestResponseDto;

public interface RequestService {

    TeacherSignupRequestResponseDto createSignupRequest(TeacherSignupRequestCreateDto signupRequest);

    TeacherSignupRequestResponseDto getSignUpRequestById(Long id);

}
