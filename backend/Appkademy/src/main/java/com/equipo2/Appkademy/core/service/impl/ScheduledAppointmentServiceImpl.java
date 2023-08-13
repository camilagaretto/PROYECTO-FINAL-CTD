package com.equipo2.Appkademy.core.service.impl;

import com.equipo2.Appkademy.core.mapper.AppkademyMapper;
import com.equipo2.Appkademy.core.model.entity.ScheduledAppointment;
import com.equipo2.Appkademy.core.model.entity.Teacher;
import com.equipo2.Appkademy.core.model.entity.WeeklyWorkingSchedule;
import com.equipo2.Appkademy.core.model.repository.ScheduledAppointmentRepository;
import com.equipo2.Appkademy.core.model.repository.StudentRepository;
import com.equipo2.Appkademy.core.model.repository.TeacherRepository;
import com.equipo2.Appkademy.core.service.ScheduledAppointmentService;
import com.equipo2.Appkademy.rest.dto.request.ScheduledAppointmentCreateRequestDto;
import com.equipo2.Appkademy.rest.error.BusinessException;
import com.equipo2.Appkademy.rest.error.ErrorCodes;
import com.equipo2.Appkademy.rest.error.NotFoundException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduledAppointmentServiceImpl implements ScheduledAppointmentService {

    @Autowired
    private AppkademyMapper mapper;

    @Autowired
    private ScheduledAppointmentRepository scheduledAppointmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public ScheduledAppointment save(ScheduledAppointmentCreateRequestDto createRequestDto) {
            Teacher teacher = assertTeacherExists(createRequestDto.getTeacherId());
            assertStudentExists(createRequestDto.getStudentId());
            assertStartAndEndDateAreInTheFuture(createRequestDto);
            assertStartDateIsBeforeEndDate(createRequestDto);
            assertAppointmentFallsWithinTeacherWorkingSchedule(teacher, createRequestDto);
            assertTeacherTimeSlotIsAvailable(teacher, createRequestDto);

            ScheduledAppointment newAppointment = new ScheduledAppointment();
            newAppointment.setStartsOn(createRequestDto.getStartsOn());
            newAppointment.setEndsOn(createRequestDto.getEndsOn());
            newAppointment.setStudentId(createRequestDto.getStudentId());
            newAppointment.setTeacherId(createRequestDto.getTeacherId());

            return scheduledAppointmentRepository.save(newAppointment);
    }

    private void assertAppointmentFallsWithinTeacherWorkingSchedule(Teacher teacher, ScheduledAppointmentCreateRequestDto createRequestDto) {
        WeeklyWorkingSchedule teacherWeeklySchedule = teacher.getWeeklyWorkingSchedule();

        //start and end date cannot be more than 8 hours apart
        if(createRequestDto.getEndsOn().isAfter(createRequestDto.getStartsOn().plusHours(8))){
            throw new BusinessException(ErrorCodes.APPOINTMENT_CANNOT_LAST_MORE_THAN_8_HOURS);
        }

        DayOfWeek appointmentDay = createRequestDto.getStartsOn().getDayOfWeek();
        List<DayOfWeek> teacherWorkingDays = getTeacherWorkingDays(teacher);
        if(!teacherWorkingDays.contains(appointmentDay)){
            throw new BusinessException(ErrorCodes.TEACHER_DOES_NOT_WORK_ON_APPOINTMENT_DAY);
        }

        if(teacherWeeklySchedule.getCheckIn().isAfter(createRequestDto.getStartsOn().toLocalTime())){
            throw new BusinessException(ErrorCodes.APPOINTMENT_CANNOT_START_BEFORE_TEACHER_CHECK_IN_HOUR);
        }

        if(teacherWeeklySchedule.getCheckOut().isBefore(createRequestDto.getEndsOn().toLocalTime())){
            throw new BusinessException(ErrorCodes.APPOINTMENT_CANNOT_END_AFTER_TEACHER_CHECK_OUT_HOUR);
        }
    }

    private List<DayOfWeek> getTeacherWorkingDays(Teacher teacher) {
        List<DayOfWeek> teacherWorkingDays = new ArrayList<>();
        if(teacher.getWeeklyWorkingSchedule().isSunday()){
            teacherWorkingDays.add(DayOfWeek.SUNDAY);
        }
        if(teacher.getWeeklyWorkingSchedule().isMonday()){
            teacherWorkingDays.add(DayOfWeek.MONDAY);
        }
        if(teacher.getWeeklyWorkingSchedule().isTuesday()){
            teacherWorkingDays.add(DayOfWeek.TUESDAY);
        }
        if(teacher.getWeeklyWorkingSchedule().isWednesday()){
            teacherWorkingDays.add(DayOfWeek.WEDNESDAY);
        }
        if(teacher.getWeeklyWorkingSchedule().isThursday()){
            teacherWorkingDays.add(DayOfWeek.THURSDAY);
        }
        if(teacher.getWeeklyWorkingSchedule().isFriday()){
            teacherWorkingDays.add(DayOfWeek.FRIDAY);
        }
        if(teacher.getWeeklyWorkingSchedule().isSaturday()){
            teacherWorkingDays.add(DayOfWeek.SATURDAY);
        }
        return teacherWorkingDays;
    }

    private void assertStudentExists(Long studentId) {
        studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException("No Student found for id: " + studentId));
    }

    private Teacher assertTeacherExists(Long teacherId) {
        return teacherRepository.findById(teacherId).orElseThrow(() -> new NotFoundException("No Teacher found for id: " + teacherId));
    }

    private void assertTeacherTimeSlotIsAvailable(Teacher teacher, ScheduledAppointmentCreateRequestDto scheduledAppointment) {
        List<ScheduledAppointment> existingAppointments = teacher.getScheduledAppointments();
        //Optional<List<ScheduledAppointment>> existingAppointments = scheduledAppointmentRepository.findByTeacherId(teacher.getId());

        //if the teacher has existing appointments validate that there is no overlapping
        if(CollectionUtils.isNotEmpty(existingAppointments)){
            //If an existing appointment end date is before the start date of the scheduled appointment I'm validating, it's of no interest for validation purposes;
            List<ScheduledAppointment> nonExpiredScheduledAppointments = existingAppointments.stream()
                    //.filter(existingAppointment -> existingAppointment.getEndsOn().isBefore(scheduledAppointment.getStartsOn()))
                    .filter(existingAppointment -> existingAppointment.getEndsOn().isAfter(LocalDateTime.now()))
                    .toList();

            //Validate an existing appointment doesnt overlap with the new appointment
            boolean overlapExists = nonExpiredScheduledAppointments.stream()
                    .anyMatch(nonExpiredAppointment -> isOverlapping(
                            nonExpiredAppointment.getStartsOn(),
                            nonExpiredAppointment.getEndsOn(),
                            scheduledAppointment.getStartsOn(),
                            scheduledAppointment.getEndsOn()));

            if(overlapExists){
                throw new BusinessException(ErrorCodes.TEACHER_ALREADY_HAS_AN_OVERLAPPING_SCHEDULED_APPOINTMENT_ON_THIS_TIMEFRAME);
            }
        }

    }

    private boolean isOverlapping(LocalDateTime appointment1StartsOn, LocalDateTime appointment1EndsOn,
                                  LocalDateTime appointment2StartsOn, LocalDateTime appointment2EndsOn) {
        return appointment1StartsOn.isBefore(appointment2EndsOn) && appointment2StartsOn.isBefore(appointment1EndsOn);
    }

    private void assertStartDateIsBeforeEndDate(ScheduledAppointmentCreateRequestDto scheduledAppointment) {
        if(scheduledAppointment.getEndsOn().isBefore(scheduledAppointment.getStartsOn())){
            throw new BusinessException(ErrorCodes.SCHEDULED_APPOINTMENT_END_DATE_CANNOT_BE_BEFORE_START_DATE);
        }
    }

    private void assertStartAndEndDateAreInTheFuture(ScheduledAppointmentCreateRequestDto scheduledAppointment) {
        if(LocalDateTime.now().isAfter(scheduledAppointment.getStartsOn())){
            throw new BusinessException(ErrorCodes.SCHEDULED_APPOINTMENT_START_DATE_CANNOT_BE_IN_THE_PAST);
        }
        if(LocalDateTime.now().isAfter(scheduledAppointment.getEndsOn())){
            throw new BusinessException(ErrorCodes.SCHEDULED_APPOINTMENT_END_DATE_CANNOT_BE_IN_THE_PAST);
        }
    }

}
