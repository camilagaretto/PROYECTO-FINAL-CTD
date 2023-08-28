package com.equipo2.Appkademy.core.mapper;

import com.equipo2.Appkademy.core.model.entity.*;
import com.equipo2.Appkademy.rest.dto.filter.TeacherFilterDto;
import com.equipo2.Appkademy.rest.dto.request.*;
import com.equipo2.Appkademy.rest.dto.response.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppkademyMapper {

    TeacherResponseDto teacherToTeacherResponseDto(Teacher teacher);

    List<TeachingProficiency> teachingProficiencyCreateRequestDtoToTeachingProficiency(List<TeachingProficiencyDto> listTeachingProficiencyDto);

    WeeklyWorkingSchedule weeklyWorkingScheduleCreateRequestDtoToWeeklyWorkginSchedule(WeeklyWorkingScheduleCreateRequestDto createRequestDto);

    Address addressRequestDtoToAddress(AddressRequestDto createRequestDto);

    AddressResponseDto addressToAddressResponseDto(Address address);

    List<TeachingProficiencyResponseDto> teachingProficiencyListToTeachingProficiencyResponseDtoList(List<TeachingProficiency> proficiencies);

    List<ScheduledAppointment> scheduledAppointmentCreateRequestDtoListToScheduledAppointmentList(List<ScheduledAppointmentCreateRequestDto> scheduledAppointmentCreateRequestDtoList);

    ScheduledAppointment scheduledAppointmentCreateRequestDtoToScheduledAppointment(ScheduledAppointmentCreateRequestDto scheduledAppointmentCreateRequestDto);

    StudentResponseDto studentToStudentResponseDto(Student student);

    ScheduledAppointmentResponseDto scheduledAppointmenttoToScheduledAppointmentResponseDto(ScheduledAppointment scheduledAppointment);

    //REQUEST
    TeacherSignupRequestResponseDto teacherSignupRequestToTeacherSignupRequestResponseDto(TeacherSignupRequest signupRequest);


    List<TeachingSubjectResponseDto> teachingSubjectListToTeachingSubjectResponseDtoList(List<TeachingSubject> entities);

    TeachingSubjectResponseDto teachingSubjectToTeachingSubjectResponseDto(TeachingSubject entity);

    CharacteristicResponseDto characteristicToCharacteristicResponseDto(Characteristic entity);

    List<Characteristic> characteristicCreateRequestDtoListToCharacteristicList(List<CharacteristicRequestDto> characteristics);

    List<CharacteristicResponseDto> characteristicListToCharacteristicResponseDtoList(List<Characteristic> resultList);

    List<StudentResponseDto> studentListToStudentResponseList(List<Student> resultList);

    List<TeacherCompactResponseDto> teacherPageToTeacherCompactResponseDto(Page<Teacher> teacherPage);

    @Mapping(target = "pageNumberSelected", source = "filter.pageNumber")
    @Mapping(target = "pageSizeSelected", source = "filter.pageSize")
    @Mapping(target = "totalPagesFound", source = "teacherPage.totalPages")
    @Mapping(target = "totalItemsFound", source = "teacherPage.totalElements")
    TeacherSearchResponseDto teacherPageToTeacherSearchResponseDto(Page<Teacher> teacherPage, TeacherFilterDto filter);

    List<TeacherCompactResponseDto> teacherListToTeacherCompactResponseDtoList(List<Teacher> content);
}
