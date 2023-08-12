package com.equipo2.Appkademy.core.mapper;

import com.equipo2.Appkademy.core.model.entity.*;
import com.equipo2.Appkademy.rest.dto.request.AddressCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.request.ScheduledAppointmentCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.request.TeachingProficiencyDto;
import com.equipo2.Appkademy.rest.dto.request.WeeklyWorkingScheduleCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.response.AddressResponseDto;
import com.equipo2.Appkademy.rest.dto.response.StudentResponseDto;
import com.equipo2.Appkademy.rest.dto.response.TeacherResponseDto;
import com.equipo2.Appkademy.rest.dto.response.TeachingProficiencyResponseDto;
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

    List<ScheduledAppointment> scheduledAppointmentCreateRequestDtoToScheduledAppointment(List<ScheduledAppointmentCreateRequestDto> scheduledAppointmentCreateRequestDtoList);

    StudentResponseDto studentToStudentResponseDto(Student student);

}
