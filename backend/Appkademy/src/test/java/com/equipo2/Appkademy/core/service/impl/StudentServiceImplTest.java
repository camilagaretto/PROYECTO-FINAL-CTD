package com.equipo2.Appkademy.core.service.impl;

import com.equipo2.Appkademy.core.mapper.AppkademyMapperImpl;
import com.equipo2.Appkademy.core.model.entity.Student;
import com.equipo2.Appkademy.core.model.repository.StudentRepository;
import com.equipo2.Appkademy.core.model.repository.TeacherRepository;
import com.equipo2.Appkademy.core.security.model.User;
import com.equipo2.Appkademy.core.security.model.repository.UserRepository;
import com.equipo2.Appkademy.core.service.NotificationService;
import com.equipo2.Appkademy.rest.dto.request.StudentCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.request.StudentUpdateRequestDto;
import com.equipo2.Appkademy.rest.dto.response.StudentResponseDto;
import com.equipo2.Appkademy.rest.error.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private NotificationService notificationService;

    @Spy
    private AppkademyMapperImpl mapper;

    @BeforeEach
    public void before(){
        Mockito.reset(studentRepository, teacherRepository, userRepository, notificationService);
    }


    @Test
    public void shouldSaveStudent(){
        Long userId = 1L;
        User user = new User();
        user.setUserId(userId);

        String name = "Julian";
        String lastName = "Bordet";

        doReturn(Optional.of(user)).when(userRepository).findById(userId);

        StudentCreateRequestDto createRequestDto = new StudentCreateRequestDto();
        createRequestDto.setFirstName(name);
        createRequestDto.setLastName(lastName);
        createRequestDto.setScheduledAppointments(null);
        createRequestDto.setUserId(userId);

        doReturn(Mockito.mock(Student.class)).when(studentRepository).save(any(Student.class));
        doReturn(user).when(userRepository).save(any(User.class));
        doNothing().when(notificationService).sendEmailNotification(anyString(), anyString());

        StudentResponseDto responseDto = studentService.save(createRequestDto);

        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository, times(1)).save(captor.capture());
        Student savedStudent = captor.getValue();

        assertNotNull(savedStudent);
        assertEquals(name, savedStudent.getFirstName());
        assertEquals(lastName, savedStudent.getLastName());
        assertEquals(userId, savedStudent.getUserId());
        assertNull(savedStudent.getScheduledAppointments());
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenUserNotFound(){
        Long userId = 1L;
        doReturn(Optional.empty()).when(userRepository).findById(userId);

        StudentCreateRequestDto createRequestDto = new StudentCreateRequestDto();
        createRequestDto.setUserId(userId);

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class,
                () -> studentService.save(createRequestDto));

        assertNotNull(exception);

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void shouldFindStudentById(){
        String name = "Julian";
        Student student = new Student();
        student.setFirstName(name);

        doReturn(Optional.of(student)).when(studentRepository).findById(1L);

        StudentResponseDto responseDto = studentService.getById(1L);

        assertNotNull(responseDto);
        assertEquals(name, responseDto.getFirstName());

        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenStudentNotFound(){
        doReturn(Optional.empty()).when(studentRepository).findById(1L);

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class,
                () -> studentService.getById(1L));

        assertNotNull(exception);
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    public void shouldUpdateStudent(){
        String name = "Julian";
        String lastName = "Bordet";

        Student student = new Student();

        doReturn(Optional.of(student)).when(studentRepository).findById(1L);

        StudentUpdateRequestDto updateRequestDto = new StudentUpdateRequestDto();
        updateRequestDto.setFirstName(name);
        updateRequestDto.setLastName(lastName);
        updateRequestDto.setScheduledAppointments(null);

        doReturn(Mockito.mock(Student.class)).when(studentRepository).save(any(Student.class));

        studentService.update(1L, updateRequestDto);

        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository, times(1)).save(captor.capture());
        Student updatedStudent = captor.getValue();

        assertNotNull(updatedStudent);
        assertEquals(updateRequestDto.getFirstName(), updatedStudent.getFirstName());
        assertEquals(updateRequestDto.getLastName(), updatedStudent.getLastName());
        assertNull(updatedStudent.getScheduledAppointments());
    }





}