package com.equipo2.Appkademy.core.service.impl;

import com.equipo2.Appkademy.core.mapper.AppkademyMapper;
import com.equipo2.Appkademy.core.model.entity.TeacherSignupRequest;
import com.equipo2.Appkademy.core.model.enums.ReviewDecision;
import com.equipo2.Appkademy.core.model.repository.TeacherSignupRequestRepository;
import com.equipo2.Appkademy.core.model.repository.TeacherTerminationRequestRepository;
import com.equipo2.Appkademy.core.service.RequestService;
import com.equipo2.Appkademy.core.validation.service.TeacherValidationServiceImpl;
import com.equipo2.Appkademy.rest.dto.request.TeacherSignupRequestCreateDto;
import com.equipo2.Appkademy.rest.dto.response.TeacherSignupRequestResponseDto;
import com.equipo2.Appkademy.rest.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private TeacherSignupRequestRepository signupRequestRepository;

    @Autowired
    private TeacherTerminationRequestRepository terminationRequestRepository;

    @Autowired
    private AppkademyMapper mapper;

    @Autowired
    private TeacherValidationServiceImpl teacherValidation;

    @Override
    public TeacherSignupRequestResponseDto createSignupRequest(TeacherSignupRequestCreateDto signupRequestDto) {
        teacherValidation.assertHourlyRatesAreValid(signupRequestDto.getTeacherFormData().getHourlyRates());

        TeacherSignupRequest entity = TeacherSignupRequest.builder()
                .firstName(signupRequestDto.getTeacherFormData().getFirstName())
                .lastName(signupRequestDto.getTeacherFormData().getLastName())
                .hourlyRates(signupRequestDto.getTeacherFormData().getHourlyRates())
                .modalities(signupRequestDto.getTeacherFormData().getModalities())
                //.proficiencies(mapper.teachingProficiencyCreateRequestDtoToTeachingProficiency(signupRequestDto.getTeacherFormData().getProficiencies()))
                .weeklyWorkingSchedule(mapper.weeklyWorkingScheduleCreateRequestDtoToWeeklyWorkginSchedule(signupRequestDto.getTeacherFormData().getWeeklyWorkingSchedule()))
                .providerCategoryId(1L)
                .profilePictureUrl(signupRequestDto.getTeacherFormData().getProfilePictureUrl())
                .shortDescription(signupRequestDto.getTeacherFormData().getShortDescription())
                .fullDescription(signupRequestDto.getTeacherFormData().getFullDescription())
                .address(mapper.addressRequestDtoToAddress(signupRequestDto.getTeacherFormData().getAddress()))
                .enabled(true)
                .createdOn(LocalDateTime.now())
                .lastModifiedOn(LocalDateTime.now())
                .totalLikes(0L)
                .requestCreatedOn(LocalDateTime.now())
                .requestHasBeenReviewed(false)
                .reviewDecision(ReviewDecision.UNDER_REVIEW)
                .build();

        return mapper.teacherSignupRequestToTeacherSignupRequestResponseDto(signupRequestRepository.save(entity));
    }

    @Override
    public TeacherSignupRequestResponseDto getSignUpRequestById(Long id) {
        TeacherSignupRequest entity = signupRequestRepository.findById(id).orElseThrow(() -> new NotFoundException(
                "No Signup Request found for id: " + id));
        return mapper.teacherSignupRequestToTeacherSignupRequestResponseDto(entity);
    }

}
