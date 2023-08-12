package com.equipo2.Appkademy.core.mapper;

import com.equipo2.Appkademy.core.model.entity.*;
import com.equipo2.Appkademy.rest.dto.request.AddressCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.request.ScheduledAppointmentCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.request.TeachingProficiencyDto;
import com.equipo2.Appkademy.rest.dto.request.WeeklyWorkingScheduleCreateRequestDto;
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

}
