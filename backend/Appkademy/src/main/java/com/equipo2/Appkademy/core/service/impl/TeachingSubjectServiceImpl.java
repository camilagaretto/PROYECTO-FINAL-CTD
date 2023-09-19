package com.equipo2.Appkademy.core.service.impl;

import com.equipo2.Appkademy.core.mapper.AppkademyMapper;
import com.equipo2.Appkademy.core.model.entity.Teacher;
import com.equipo2.Appkademy.core.model.entity.TeachingProficiency;
import com.equipo2.Appkademy.core.model.entity.TeachingSubject;
import com.equipo2.Appkademy.core.model.enums.TeachingMasteryLevel;
import com.equipo2.Appkademy.core.model.repository.TeacherRepository;
import com.equipo2.Appkademy.core.model.repository.TeachingProficiencyRepository;
import com.equipo2.Appkademy.core.model.repository.TeachingSubjectRepository;
import com.equipo2.Appkademy.core.service.TeachingProficiencyService;
import com.equipo2.Appkademy.rest.dto.filter.PageableFilter;
import com.equipo2.Appkademy.rest.dto.request.TeachingSubjectDto;
import com.equipo2.Appkademy.rest.dto.response.TeachingSubjectResponseDto;
import com.equipo2.Appkademy.rest.dto.response.TeachingSubjectSearchResponseDto;
import com.equipo2.Appkademy.rest.error.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TeachingSubjectServiceImpl implements TeachingProficiencyService {

    @Autowired
    private TeachingSubjectRepository subjectRepository;

    @Autowired
    private TeachingProficiencyRepository teachingProficiencyRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AppkademyMapper mapper;

    @Override
    public TeachingSubjectResponseDto create(TeachingSubjectDto createDto){
        Optional<TeachingSubject> optionalEntity = subjectRepository.findByName(createDto.getName());
        if(optionalEntity.isPresent()){
            return mapper.teachingSubjectToTeachingSubjectResponseDto(optionalEntity.get());
        }
        TeachingSubject entity = subjectRepository.save(new TeachingSubject(createDto.getName()));

        //create proficiencies for new subject
        teachingProficiencyRepository.save(new TeachingProficiency(TeachingMasteryLevel.COLLEGE, entity));
        teachingProficiencyRepository.save(new TeachingProficiency(TeachingMasteryLevel.HIGHSCHOOL, entity));
        teachingProficiencyRepository.save(new TeachingProficiency(TeachingMasteryLevel.MIDDLE_SCHOOL, entity));

        return mapper.teachingSubjectToTeachingSubjectResponseDto(entity);
    }

    @Override
    public TeachingSubjectSearchResponseDto search(PageableFilter filter) {
        if(Objects.isNull(filter.getPageNumber())){
            filter.setPageNumber(1);
        } else {
            filter.setPageNumber(filter.getPageNumber());
        }

        if(Objects.isNull(filter.getPageSize())){
            filter.setPageSize(10);
        }

        PageRequest pageable = PageRequest.of(filter.getPageNumber()-1, filter.getPageSize());

        Page<TeachingSubject> resultPage = subjectRepository.findAll(pageable);
        List<TeachingSubject> resultList = resultPage.getContent();

        TeachingSubjectSearchResponseDto searchResponseDto = new TeachingSubjectSearchResponseDto();
        searchResponseDto.setTotalItemsFound(resultPage.getTotalElements());
        searchResponseDto.setTotalPagesFound(resultPage.getTotalPages());
        searchResponseDto.setPageSizeSelected(filter.getPageSize());
        searchResponseDto.setPageNumberSelected(filter.getPageNumber());
        searchResponseDto.setSearchResults(mapper.teachingSubjectListToTeachingSubjectResponseDtoList(resultList));

        return searchResponseDto;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        TeachingSubject subjectToDelete = subjectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("TeachingSubject not found with ID: " + id));

        //Find teachers and remove proficiencies that will be deleted
        List<TeachingProficiency> proficienciesToDelete = teachingProficiencyRepository.findBySubjectId(id);

        List<Teacher> teachers = teacherRepository.findAll();

        for(TeachingProficiency proficiency : proficienciesToDelete){
            List<Teacher> teachersWithProficiency = teachers.stream().filter(teacher -> teacher.getProficiencies().contains(proficiency)).toList();
            for(Teacher teacher : teachersWithProficiency){
                teacher.removeProficiency(proficiency);
            }
        }

        teacherRepository.saveAll(teachers);
        teachingProficiencyRepository.deleteBySubjectId(id);
        subjectRepository.deleteById(id);
    }


}
