package com.equipo2.Appkademy.core.service.impl;

import com.equipo2.Appkademy.core.mapper.AppkademyMapper;
import com.equipo2.Appkademy.core.model.entity.Student;
import com.equipo2.Appkademy.core.model.repository.StudentRepository;
import com.equipo2.Appkademy.core.service.StudentService;
import com.equipo2.Appkademy.rest.dto.request.StudentCreateRequestDto;
import com.equipo2.Appkademy.rest.error.BadRequestException;
import com.equipo2.Appkademy.rest.error.BusinessException;
import com.equipo2.Appkademy.rest.error.ErrorCodes;
import com.equipo2.Appkademy.rest.error.NotFoundException;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRespository;

    @Autowired
    private AppkademyMapper mapper;

    @Override
    public Student getById(Long id) {
        return studentRespository.findById(id).orElseThrow(() -> new NotFoundException("No Student found for id: " + id));
    }

    @Override
    public Student save(StudentCreateRequestDto createRequestDto) {
        //TODO: validations?
        assertStudentDoesNotAlreadyExist(createRequestDto);
        assertEmailIsValid(createRequestDto.getEmail());

        Student entity = Student.builder()
                                .firstName(createRequestDto.getFirstName())
                                .lastName(createRequestDto.getLastName())
                                .email(createRequestDto.getEmail())
                                .address(mapper.addressCreateRequestDtoToAddress(createRequestDto.getAddress()))
                                .scheduledAppointments(mapper.scheduledAppointmentCreateRequestDtoListToScheduledAppointmentList(createRequestDto.getScheduledAppointments()))
                                .enabled(true)
                                .createdOn(LocalDateTime.now())
                                .lastModifiedOn(LocalDateTime.now())
                                .build();

        return studentRespository.save(entity);
    }

    private void assertEmailIsValid(String email) {
        if(!EmailValidator.getInstance().isValid(email)){
            throw new BadRequestException("email", email);
        };
    }

    private void assertStudentDoesNotAlreadyExist(StudentCreateRequestDto createRequestDto) {
        if(studentRespository.findByEmail(createRequestDto.getEmail()).isPresent()){
            throw new BusinessException(ErrorCodes.STUDENT_WITH_SAME_EMAIL_ALREADY_EXISTS);
        }
    }
}
