package com.equipo2.Appkademy.rest.controller;

import com.equipo2.Appkademy.core.mapper.AppkademyMapper;
import com.equipo2.Appkademy.core.model.entity.Student;
import com.equipo2.Appkademy.core.service.StudentService;
import com.equipo2.Appkademy.rest.dto.request.StudentCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.response.StudentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/v1/categories/1/customers/")
public class StudentController implements IStudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AppkademyMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getById(@PathVariable Long id){
        Student entity = studentService.getById(id);
        return ResponseEntity.ok(mapper.studentToStudentResponseDto(entity));
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> create(@RequestBody StudentCreateRequestDto createRequestDto){
        Student entity = studentService.save(createRequestDto);
        return new ResponseEntity<StudentResponseDto>(mapper.studentToStudentResponseDto(entity), HttpStatus.CREATED);
    }

}
