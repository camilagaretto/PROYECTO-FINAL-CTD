package com.equipo2.Appkademy.core.mapper;

import com.equipo2.Appkademy.core.model.entity.*;
import com.equipo2.Appkademy.rest.dto.request.*;
import com.equipo2.Appkademy.rest.dto.response.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppkademyMapper {

    TeacherResponseDto teacherToTeacherResponseDto(Teacher teacher);

    List<TeachingProficiency> teachingProficiencyCreateRequestDtoToTeachingProficiency(List<TeachingProficiencyDto> listTeachingProficiencyDto);

    WeeklyWorkingSchedule weeklyWorkingScheduleCreateRequestDtoToWeeklyWorkginSchedule(WeeklyWorkingScheduleCreateRequestDto createRequestDto);

    Address addressCreateRequestDtoToAddress(AddressCreateRequestDto createRequestDto);

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

    List<Characteristic> characteristicCreateRequestDtoListToCharacteristicList(List<CharacteristicCreateRequestDto> characteristics);
}
