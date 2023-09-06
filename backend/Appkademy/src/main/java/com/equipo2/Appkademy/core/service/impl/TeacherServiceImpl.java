package com.equipo2.Appkademy.core.service.impl;

import com.equipo2.Appkademy.core.mapper.AppkademyMapper;
import com.equipo2.Appkademy.core.model.entity.Characteristic;
import com.equipo2.Appkademy.core.model.entity.Teacher;
import com.equipo2.Appkademy.core.model.entity.TeachingProficiency;
import com.equipo2.Appkademy.core.model.enums.UserType;
import com.equipo2.Appkademy.core.model.repository.TeacherRepository;
import com.equipo2.Appkademy.core.model.repository.TeachingProficiencyRepository;
import com.equipo2.Appkademy.core.security.model.User;
import com.equipo2.Appkademy.core.security.model.repository.UserRepository;
import com.equipo2.Appkademy.core.service.TeacherService;
import com.equipo2.Appkademy.core.specs.TeacherSpec;
import com.equipo2.Appkademy.core.validation.service.TeacherValidationServiceImpl;
import com.equipo2.Appkademy.rest.dto.filter.TeacherFilterDto;
import com.equipo2.Appkademy.rest.dto.request.TeacherCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.request.TeacherUpdateRequestDto;
import com.equipo2.Appkademy.rest.dto.response.ScheduledAppointmentResponseDto;
import com.equipo2.Appkademy.rest.dto.response.TeacherResponseDto;
import com.equipo2.Appkademy.rest.dto.response.TeacherSearchResponseDto;
import com.equipo2.Appkademy.rest.error.ErrorCodes;
import com.equipo2.Appkademy.rest.error.NotFoundException;
import jakarta.persistence.EntityManager;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeachingProficiencyRepository teachingProficiencyRepository;

    @Autowired
    private AppkademyMapper mapper;

    @Autowired
    private TeacherValidationServiceImpl teacherValidationService;

    /* TODO TO BE IMPLEMENTED
    @Autowired
    private List<TeacherFillerService> teacherFillerServices;
     */

    @Autowired
    EntityManager entityManager;

    @Override
    public TeacherResponseDto getById(Long id) {
        Teacher entity = teacherRepository.findById(id).orElseThrow(
                () -> new NotFoundException("No Teacher found for id: " + id));
        if(CollectionUtils.isNotEmpty(entity.getScheduledAppointments())){
            return removeExpiredAppointmentsFromResponse(mapper.teacherToTeacherResponseDto(entity));
        }
        return mapper.teacherToTeacherResponseDto(entity);
    }

    @Override
    public TeacherResponseDto save(TeacherCreateRequestDto createRequestDto) {
        teacherValidationService.assertUserDoesNotAlreadyExist(createRequestDto.getUserId());
        teacherValidationService.assertHourlyRatesAreValid(createRequestDto.getHourlyRates());

        List<TeachingProficiency> teachingProficiencyEntities = teachingProficiencyRepository.findAllById(createRequestDto.getProficiencyIds());

        teacherValidationService.assertAllTeachingProficienciesExist(teachingProficiencyEntities, createRequestDto.getProficiencyIds());

        List<Characteristic> characteristicEntities = null;
        if(CollectionUtils.isNotEmpty(createRequestDto.getCharacteristicIds())){
            characteristicEntities = teacherValidationService.assertCharacteristicsExists(createRequestDto.getCharacteristicIds());
        }

        User user = userRepository.findById(createRequestDto.getUserId()).orElseThrow(() -> new NotFoundException(ErrorCodes.USER_NOT_FOUND));

        Teacher teacher = Teacher.builder()
                .userId(createRequestDto.getUserId())
                .firstName(createRequestDto.getFirstName())
                .lastName(createRequestDto.getLastName())
                .hourlyRates(createRequestDto.getHourlyRates())
                .modalities(createRequestDto.getModalities())
                .proficiencies(teachingProficiencyEntities)
                .weeklyWorkingSchedule(mapper.weeklyWorkingScheduleCreateRequestDtoToWeeklyWorkginSchedule(createRequestDto.getWeeklyWorkingSchedule()))
                .providerCategoryId(1L)
                .profilePictureUrl(createRequestDto.getProfilePictureUrl())
                .shortDescription(createRequestDto.getShortDescription())
                .fullDescription(createRequestDto.getFullDescription())
                .address(mapper.addressRequestDtoToAddress(createRequestDto.getAddress()))
                .enabled(true)
                .createdOn(LocalDateTime.now())
                .lastModifiedOn(LocalDateTime.now())
                .totalLikes(0L)
                .characteristics(characteristicEntities)
                .signupApprovedByAdmin(true)
                .build();

        Teacher entity = teacherRepository.save(teacher);

        user.setType(UserType.TEACHER);
        user.setUserTypeId(entity.getId());
        userRepository.save(user);

        return mapper.teacherToTeacherResponseDto(entity);
    }

    @Override
    public TeacherSearchResponseDto search(TeacherFilterDto filter) {
        if(Objects.isNull(filter.getPageNumber())){
            filter.setPageNumber(1);
        }
        if(Objects.isNull(filter.getPageSize())){
            filter.setPageSize(10);
        }

        Specification<Teacher> searchSpec = generateSearchSpecFromFilter(filter);
        Page<Teacher> teacherPage = teacherRepository.findAll(searchSpec,
                PageRequest.of(filter.getPageNumber()-1, filter.getPageSize()));

        TeacherSearchResponseDto searchResponseDto = mapper.teacherPageToTeacherSearchResponseDto(teacherPage, filter);
        searchResponseDto.setSearchResults(mapper.teacherListToTeacherCompactResponseDtoList(teacherPage.getContent()));
        
        return searchResponseDto;
    }

    /* TODO TO BE IMPLEMENTED
    @Override
    public Teacher patch(Long id, TeacherPatchRequestDto patchRequestDto) {
        Teacher entity = teacherRepository.findById(id).orElseThrow(
                () -> new NotFoundException("No Teacher found for id: " + id));

        teacherFillerServices.forEach(filler -> filler.fill(entity, patchRequestDto));
        return teacherRepository.save(entity);
    }

     */

    @Override
    public void delete(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new NotFoundException("No Teacher found for id: " + id));

        //remove associations
        teacher.setProficiencies(new ArrayList<>());
        teacherRepository.save(teacher);

        teacherRepository.deleteById(id);
    }

    @Override
    public TeacherResponseDto update(Long id, TeacherUpdateRequestDto updateRequestDto) {
        Teacher entity = teacherRepository.findById(id).orElseThrow(() -> new NotFoundException(
                ErrorCodes.TEACHER_NOT_FOUND));

        teacherValidationService.assertHourlyRatesAreValid(updateRequestDto.getHourlyRates());

        List<TeachingProficiency> teachingProficiencyEntities = teachingProficiencyRepository.findAllById(updateRequestDto.getProficiencyIds());

        teacherValidationService.assertAllTeachingProficienciesExist(teachingProficiencyEntities, updateRequestDto.getProficiencyIds());

        List<Characteristic> characteristicEntities = null;
        if(CollectionUtils.isNotEmpty(updateRequestDto.getCharacteristicIds())){
            characteristicEntities = teacherValidationService.assertCharacteristicsExists(updateRequestDto.getCharacteristicIds());
        }

        //Update attributes
        entity.setFirstName(updateRequestDto.getFirstName());
        entity.setLastName(updateRequestDto.getLastName());
        entity.setHourlyRates(updateRequestDto.getHourlyRates());
        entity.setModalities(updateRequestDto.getModalities());
        entity.setProficiencies(teachingProficiencyEntities);
        entity.setWeeklyWorkingSchedule(mapper.weeklyWorkingScheduleCreateRequestDtoToWeeklyWorkginSchedule(updateRequestDto.getWeeklyWorkingSchedule()));
        entity.setProfilePictureUrl(updateRequestDto.getProfilePictureUrl());
        entity.setShortDescription(updateRequestDto.getShortDescription());
        entity.setFullDescription(updateRequestDto.getFullDescription());
        entity.setAddress(mapper.addressRequestDtoToAddress(updateRequestDto.getAddress()));
        entity.setEnabled(updateRequestDto.getEnabled());
        entity.setLastModifiedOn(LocalDateTime.now());
        entity.setTotalLikes(updateRequestDto.getTotalLikes());
        entity.setCharacteristics(characteristicEntities);

        return mapper.teacherToTeacherResponseDto(teacherRepository.save(entity));
    }

    private Specification<Teacher> generateSearchSpecFromFilter(TeacherFilterDto filter) {
        List<Specification<Teacher>> specList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(filter.getTeacherIds())){
            specList.add(TeacherSpec.teacherIdsIn(filter.getTeacherIds()));
        }
        if(Objects.nonNull(filter.getCountry())){
            specList.add(TeacherSpec.countryEquals(filter.getCountry()));
        }
        if(Objects.nonNull(filter.getProvince())){
            specList.add(TeacherSpec.provinceEquals(filter.getProvince()));
        }
        if(Objects.nonNull(filter.getCity())){
            specList.add(TeacherSpec.cityEquals(filter.getCity()));
        }
        if(Objects.nonNull(filter.getTeachingProficiency())){
            if(Objects.nonNull(filter.getTeachingProficiency().getSubject())){
                specList.add(TeacherSpec.proficiencySubject(filter.getTeachingProficiency().getSubject().getName()));
            }
            if(Objects.nonNull(filter.getTeachingProficiency().getMasteryLevel())){
                specList.add(TeacherSpec.proficiencyMasteryLevel(filter.getTeachingProficiency().getMasteryLevel()));
            }
        }
        if(Objects.nonNull(filter.getFreeOn())){
           specList.add(TeacherSpec.teacherWithoutScheduledAppointmentAtStartTime(filter.getFreeOn()));
        }
        return Specification.allOf(specList);
    }

    private TeacherResponseDto removeExpiredAppointmentsFromResponse(TeacherResponseDto teacherResponseDto) {
        List<ScheduledAppointmentResponseDto> appointments = teacherResponseDto.getScheduledAppointments();

        List<ScheduledAppointmentResponseDto> filteredAppointments = appointments.stream().filter(appt ->
                LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0)
                        .isBefore(appt.getStartsOn())).toList();

        teacherResponseDto.setScheduledAppointments(filteredAppointments);
        return teacherResponseDto;
    }

}
