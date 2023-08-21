package com.equipo2.Appkademy.core.service.impl;

import com.equipo2.Appkademy.core.mapper.AppkademyMapper;
import com.equipo2.Appkademy.core.model.entity.Teacher;
import com.equipo2.Appkademy.core.model.repository.TeacherRepository;
import com.equipo2.Appkademy.core.service.TeacherService;
import com.equipo2.Appkademy.core.validation.TeacherValidation;
import com.equipo2.Appkademy.rest.dto.filter.TeacherFilterDto;
import com.equipo2.Appkademy.rest.dto.request.TeacherCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.response.TeacherCompactResponseDto;
import com.equipo2.Appkademy.rest.dto.response.TeacherSearchResponseDto;
import com.equipo2.Appkademy.rest.error.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private AppkademyMapper mapper;

    @Autowired
    private TeacherValidation teacherValidation;

    /* TODO TO BE IMPLEMENTED
    @Autowired
    private List<TeacherFillerService> teacherFillerServices;
     */

    @Autowired
    EntityManager entityManager;

    @Override
    public Teacher getById(Long id) {
        return teacherRepository.findById(id).orElseThrow(() -> new NotFoundException("No Teacher found for id: " + id));
    }

    @Override
    public Teacher save(TeacherCreateRequestDto createRequestDto) {
        teacherValidation.assertUserDoesNotAlreadyExist(createRequestDto.getUserId());
        teacherValidation.assertTeacherDoesNotAlreadyExist(createRequestDto.getEmail());
        TeacherValidation.assertEmailIsValid(createRequestDto.getEmail());
        TeacherValidation.assertHourlyRatesAreValid(createRequestDto.getHourlyRates());
        teacherValidation.assertTeachingSubjectsExist(createRequestDto.getProficiencies());
        if(CollectionUtils.isNotEmpty(createRequestDto.getCharacteristics())){
            teacherValidation.assertCharacteristicsExists(createRequestDto.getCharacteristics());
        }

        Teacher entity = Teacher.builder()
                .userId(createRequestDto.getUserId())
                .firstName(createRequestDto.getFirstName())
                .lastName(createRequestDto.getLastName())
                .email(createRequestDto.getEmail())
                .hourlyRates(createRequestDto.getHourlyRates())
                .modalities(createRequestDto.getModalities())
                .proficiencies(mapper.teachingProficiencyCreateRequestDtoToTeachingProficiency(createRequestDto.getProficiencies()))
                .weeklyWorkingSchedule(mapper.weeklyWorkingScheduleCreateRequestDtoToWeeklyWorkginSchedule(createRequestDto.getWeeklyWorkingSchedule()))
                .providerCategoryId(1L)
                .profilePictureUrl(createRequestDto.getProfilePictureUrl())
                .shortDescription(createRequestDto.getShortDescription())
                .fullDescription(createRequestDto.getFullDescription())
                .address(mapper.addressCreateRequestDtoToAddress(createRequestDto.getAddress()))
                .enabled(true)
                .createdOn(LocalDateTime.now())
                .lastModifiedOn(LocalDateTime.now())
                .totalLikes(0L)
                .characteristics(mapper.characteristicCreateRequestDtoListToCharacteristicList(createRequestDto.getCharacteristics()))
                .signupApprovedByAdmin(true)
                .build();

        return teacherRepository.save(entity);
    }

    @Override
    public TeacherSearchResponseDto search(TeacherFilterDto filter) {
        if(Objects.isNull(filter.getPageNumber())){
            filter.setPageNumber(1);
        }
        if(Objects.isNull(filter.getPageSize())){
            filter.setPageSize(10);
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Teacher> criteriaQuery = criteriaBuilder.createQuery(Teacher.class);
        Root<Teacher> root = criteriaQuery.from(Teacher.class);

        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Teacher> countRoot = countQuery.from(Teacher.class);

        List<Predicate> predicateListTeacher = new ArrayList<>();
        List<Predicate> predicateListCount = new ArrayList<>();


        if(CollectionUtils.isNotEmpty(filter.getTeacherIds())){
            Predicate condition = criteriaBuilder.and(root.get("id").in(filter.getTeacherIds()));
            predicateListTeacher.add(condition);
            predicateListCount.add(criteriaBuilder.and(countRoot.get("id").in(filter.getTeacherIds())));
        }
        if(Objects.nonNull(filter.getCountry())){
            Predicate condition = criteriaBuilder.equal(root.get("address").get("country"), filter.getCountry());
            predicateListTeacher.add(condition);
            predicateListCount.add(criteriaBuilder.equal(countRoot.get("address").get("country"), filter.getCountry()));
        }
        if(Objects.nonNull(filter.getProvince())){
            Predicate condition = criteriaBuilder.equal(root.get("address").get("province"), filter.getProvince());
            predicateListTeacher.add(condition);
            predicateListCount.add(criteriaBuilder.equal(countRoot.get("address").get("province"), filter.getProvince()));
        }
        if(Objects.nonNull(filter.getCity())){
            Predicate condition = criteriaBuilder.equal(root.get("address").get("city"), filter.getCity());
            predicateListTeacher.add(condition);
            predicateListCount.add(criteriaBuilder.equal(countRoot.get("address").get("city"), filter.getCity()));
        }
        if(Objects.nonNull(filter.getTeachingProficiency())){
            root.join("proficiencies");
            countRoot.join("proficiencies");
            if(Objects.nonNull(filter.getTeachingProficiency().getSubject())){
                Predicate condition = criteriaBuilder.equal(
                        root.join("proficiencies").get("subject"), filter.getTeachingProficiency().getSubject());
                predicateListTeacher.add(condition);
                predicateListCount.add(criteriaBuilder.equal(
                        countRoot.join("proficiencies").get("subject"), filter.getTeachingProficiency().getSubject()));
            }
            if(Objects.nonNull(filter.getTeachingProficiency().getMasteryLevel())){
                Predicate condition = criteriaBuilder.equal(
                        root.join("proficiencies").get("masteryLevel"), filter.getTeachingProficiency().getMasteryLevel());
                predicateListTeacher.add(condition);
                predicateListCount.add(criteriaBuilder.equal(
                        countRoot.join("proficiencies").get("masteryLevel"), filter.getTeachingProficiency().getMasteryLevel()));
            }
        }

        Predicate[] predicateArrayTeacher = predicateListTeacher.toArray(new Predicate[predicateListTeacher.size()]);
        Predicate[] predicateArrayCount = predicateListCount.toArray(new Predicate[predicateListCount.size()]);

        criteriaQuery.select(root).where(predicateArrayTeacher);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicateArrayCount);

        TypedQuery<Teacher> typedQuery = entityManager.createQuery(criteriaQuery);
        TypedQuery<Long> typedCountQuery = entityManager.createQuery(countQuery);


        // Implement pagination
        int pageNumber = filter.getPageNumber(); // Page number (starting from 1)
        int pageSize = filter.getPageSize();  // Number of results per page
        int startIndex = (pageNumber - 1) * pageSize;

        typedQuery.setFirstResult(startIndex);
        typedQuery.setMaxResults(pageSize);

        List<Teacher> resultList = typedQuery.getResultList();
        Long totalCount = typedCountQuery.getSingleResult();

        int totalPagesFound = (int) Math.ceil((double)totalCount / filter.getPageSize());


        TeacherSearchResponseDto searchResponseDto = new TeacherSearchResponseDto();
        if(CollectionUtils.isNotEmpty(resultList)){
            searchResponseDto.setPageNumberSelected(filter.getPageNumber());
            searchResponseDto.setPageSizeSelected(filter.getPageSize());
            searchResponseDto.setTotalPagesFound(totalPagesFound);
            searchResponseDto.setTotalItemsFound(totalCount);
            List<TeacherCompactResponseDto> compactTeacherList = resultList
                    .stream()
                    .map(teacher -> {
                        TeacherCompactResponseDto compactResponseDto = new TeacherCompactResponseDto();
                        compactResponseDto.setId(teacher.getId());
                        compactResponseDto.setFirstName(teacher.getFirstName());
                        compactResponseDto.setLastName(teacher.getLastName());
                        compactResponseDto.setProviderCategoryId(teacher.getProviderCategoryId());
                        compactResponseDto.setIdentityVerified(teacher.isIdentityVerified());
                        compactResponseDto.setAddress(mapper.addressToAddressResponseDto(teacher.getAddress()));
                        compactResponseDto.setProfilePictureUrl(teacher.getProfilePictureUrl());
                        compactResponseDto.setShortDescription(teacher.getShortDescription());
                        compactResponseDto.setTotalLikes(teacher.getTotalLikes());
                        compactResponseDto.setProficiencies(mapper.
                                teachingProficiencyListToTeachingProficiencyResponseDtoList(teacher.getProficiencies()));
                        return compactResponseDto;
                    })
                    .toList();

            searchResponseDto.setSearchResults(compactTeacherList);
        }
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
        teacherRepository.findById(id).orElseThrow(() -> new NotFoundException("No Teacher found for id: " + id));
        teacherRepository.deleteById(id);
    }




}
