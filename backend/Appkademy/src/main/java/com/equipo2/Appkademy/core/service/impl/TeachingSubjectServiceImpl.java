package com.equipo2.Appkademy.core.service.impl;

import com.equipo2.Appkademy.core.mapper.AppkademyMapper;
import com.equipo2.Appkademy.core.model.entity.TeachingSubject;
import com.equipo2.Appkademy.core.model.repository.TeachingSubjectRepository;
import com.equipo2.Appkademy.core.service.TeachingProficiencyService;
import com.equipo2.Appkademy.rest.dto.filter.PageableFilter;
import com.equipo2.Appkademy.rest.dto.request.TeachingSubjectDto;
import com.equipo2.Appkademy.rest.dto.response.TeachingSubjectSearchResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TeachingSubjectServiceImpl implements TeachingProficiencyService {

    @Autowired
    private TeachingSubjectRepository subjectRepository;

    @Autowired
    private AppkademyMapper mapper;

    @Override
    public TeachingSubject create(TeachingSubjectDto createDto){
        if(subjectRepository.findByName(createDto.getName()).isPresent()){
            return subjectRepository.findByName(createDto.getName()).get();
        }
        return subjectRepository.save(new TeachingSubject(createDto.getName()));
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

}
