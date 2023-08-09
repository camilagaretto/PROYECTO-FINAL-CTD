package com.equipo2.Appkademy.rest.controller;

import com.equipo2.Appkademy.core.mapper.AppkademyMapper;
import com.equipo2.Appkademy.core.model.entity.Teacher;
import com.equipo2.Appkademy.core.service.TeacherService;
import com.equipo2.Appkademy.rest.dto.filter.TeacherFilterDto;
import com.equipo2.Appkademy.rest.dto.request.TeacherCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.request.TeacherPatchRequestDto;
import com.equipo2.Appkademy.rest.dto.response.TeacherResponseDto;
import com.equipo2.Appkademy.rest.dto.response.TeacherSearchResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/v1/categories/1/providers/")
public class TeacherProviderController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private AppkademyMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponseDto> get(@PathVariable Long id){
        Teacher teacher = teacherService.getById(id);
        TeacherResponseDto responseDto = mapper.teacherToTeacherResponseDto(teacher);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    public ResponseEntity<TeacherResponseDto> create(@Valid @RequestBody TeacherCreateRequestDto TeacherCreateRequestDto){
        Teacher teacher = teacherService.save(TeacherCreateRequestDto);
        TeacherResponseDto responseDto = mapper.teacherToTeacherResponseDto(teacher);
        return new ResponseEntity<TeacherResponseDto>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<TeacherSearchResponseDto> search(@RequestBody TeacherFilterDto filter){
        TeacherSearchResponseDto searchResponse = teacherService.search(filter);
        return ResponseEntity.ok(searchResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TeacherResponseDto> patch(@PathVariable Long id,
                                                          @RequestBody TeacherPatchRequestDto patchRequestDto){
        Teacher teacher = teacherService.patch(id, patchRequestDto);
        TeacherResponseDto responseDto = mapper.teacherToTeacherResponseDto(teacher);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        teacherService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
