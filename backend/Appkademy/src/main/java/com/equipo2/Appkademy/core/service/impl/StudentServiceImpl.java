package com.equipo2.Appkademy.core.service.impl;

import com.equipo2.Appkademy.core.mapper.AppkademyMapper;
import com.equipo2.Appkademy.core.model.entity.Student;
import com.equipo2.Appkademy.core.model.entity.Teacher;
import com.equipo2.Appkademy.core.model.enums.UserType;
import com.equipo2.Appkademy.core.model.repository.StudentRepository;
import com.equipo2.Appkademy.core.model.repository.TeacherRepository;
import com.equipo2.Appkademy.core.security.model.User;
import com.equipo2.Appkademy.core.security.model.repository.UserRepository;
import com.equipo2.Appkademy.core.service.StudentService;
import com.equipo2.Appkademy.core.service.TeacherService;
import com.equipo2.Appkademy.rest.dto.filter.PageableFilter;
import com.equipo2.Appkademy.rest.dto.request.StudentCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.request.StudentUpdateRequestDto;
import com.equipo2.Appkademy.rest.dto.response.StudentSearchResponseDto;
import com.equipo2.Appkademy.rest.error.BadRequestException;
import com.equipo2.Appkademy.rest.error.ErrorCodes;
import com.equipo2.Appkademy.rest.error.NotFoundException;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppkademyMapper mapper;

    @Autowired
    private TeacherService teacherService;

    @Override
    public Student getById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new NotFoundException("No Student found for id: " + id));
    }

    @Override
    public Student update(Long id, StudentUpdateRequestDto updateRequestDto) {
        Student entity = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("No Student found for id: " + id));

        entity.setFirstName(updateRequestDto.getFirstName());
        entity.setLastName(updateRequestDto.getLastName());
        entity.setScheduledAppointments(mapper.scheduledAppointmentCreateRequestDtoListToScheduledAppointmentList(updateRequestDto.getScheduledAppointments()));
        entity.setLastModifiedOn(LocalDateTime.now());

        return studentRepository.save(entity);
    }

    @Override
    public StudentSearchResponseDto search(PageableFilter filter) {
        if(Objects.isNull(filter.getPageNumber())){
            filter.setPageNumber(1);
        } else {
            filter.setPageNumber(filter.getPageNumber());
        }

        if(Objects.isNull(filter.getPageSize())){
            filter.setPageSize(10);
        }

        PageRequest pageable = PageRequest.of(filter.getPageNumber()-1, filter.getPageSize());

        Page<Student> resultPage = studentRepository.findAll(pageable);
        List<Student> resultList = resultPage.getContent();

        StudentSearchResponseDto searchResponseDto = new StudentSearchResponseDto();
        searchResponseDto.setTotalItemsFound(resultPage.getTotalElements());
        searchResponseDto.setTotalPagesFound(resultPage.getTotalPages());
        searchResponseDto.setPageSizeSelected(filter.getPageSize());
        searchResponseDto.setPageNumberSelected(filter.getPageNumber());
        searchResponseDto.setSearchResults(mapper.studentListToStudentResponseList(resultList));

        return searchResponseDto;
    }

    @Override
    public Student save(StudentCreateRequestDto createRequestDto) {
        User user = userRepository.findById(createRequestDto.getUserId()).orElseThrow(() -> new NotFoundException(ErrorCodes.USER_NOT_FOUND));

        Student student = Student.builder()
                                .userId(createRequestDto.getUserId())
                                .firstName(createRequestDto.getFirstName())
                                .lastName(createRequestDto.getLastName())
                                //.address(mapper.addressCreateRequestDtoToAddress(createRequestDto.getAddress()))
                                .scheduledAppointments(mapper.scheduledAppointmentCreateRequestDtoListToScheduledAppointmentList(createRequestDto.getScheduledAppointments()))
                                .enabled(true)
                                .createdOn(LocalDateTime.now())
                                .lastModifiedOn(LocalDateTime.now())
                                .build();

        Student entity = studentRepository.save(student);

        user.setType(UserType.STUDENT);
        user.setUserTypeId(entity.getId());
        userRepository.save(user);

        return entity;
    }

    private void assertEmailIsValid(String email) {
        if(!EmailValidator.getInstance().isValid(email)){
            throw new BadRequestException("email", email);
        };
    }

    private void markFavoriteTeacher(Long id, Long teacherId) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No Student found for id: " + id));

        Teacher teacher = teacherService.getById(teacherId);

        List<Long> likedProviderIds = student.getLikedProviderIds();
        if (!likedProviderIds.contains(teacherId)) {
            likedProviderIds.add(teacherId);
            studentRepository.save(student);

            teacher.setTotalLikes(teacher.getTotalLikes() + 1);
            teacherRepository.save(teacher);
        }
    }

}
