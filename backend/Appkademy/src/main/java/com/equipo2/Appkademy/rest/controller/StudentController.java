package com.equipo2.Appkademy.rest.controller;

import com.equipo2.Appkademy.core.mapper.AppkademyMapper;
import com.equipo2.Appkademy.core.service.StudentService;
import com.equipo2.Appkademy.rest.dto.filter.PageableFilter;
import com.equipo2.Appkademy.rest.dto.request.StudentCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.request.StudentUpdateRequestDto;
import com.equipo2.Appkademy.rest.dto.response.StudentResponseDto;
import com.equipo2.Appkademy.rest.dto.response.StudentSearchResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.equipo2.Appkademy.core.security.model.PermissionConstants.*;

@RestController
@CrossOrigin(
        origins = "http://localhost:5173",
        allowedHeaders = "*",
        exposedHeaders = "*",
        methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(path = "/v1/categories/1/customers/")
public class StudentController implements IStudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AppkademyMapper mapper;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getById(@PathVariable Long id){
        StudentResponseDto responseDto = studentService.getById(id);
        return ResponseEntity.ok(responseDto);
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAuthority('" + STUDENT_CREATE + "')")
    public ResponseEntity<StudentResponseDto> create(@RequestBody StudentCreateRequestDto createRequestDto){
        StudentResponseDto responseDto = studentService.save(createRequestDto);
        return new ResponseEntity<StudentResponseDto>(responseDto, HttpStatus.CREATED);
    }

    @Override
    @PostMapping("/search")
    @PreAuthorize("hasAuthority('" + STUDENT_READ + "')")
    public ResponseEntity<StudentSearchResponseDto> search(@RequestBody PageableFilter filter){
        StudentSearchResponseDto searchResponse = studentService.search(filter);
        return ResponseEntity.ok(searchResponse);
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('" + STUDENT_UPDATE + "')")
    public ResponseEntity<StudentResponseDto> update(Long id, @RequestBody StudentUpdateRequestDto updateRequestDto){
        StudentResponseDto responseDto = studentService.update(id, updateRequestDto);
        return new ResponseEntity<StudentResponseDto>(responseDto, HttpStatus.CREATED);
    }

}
