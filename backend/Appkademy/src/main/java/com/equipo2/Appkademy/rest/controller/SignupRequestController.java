package com.equipo2.Appkademy.rest.controller;

import com.equipo2.Appkademy.core.mapper.AppkademyMapper;
import com.equipo2.Appkademy.core.model.entity.TeacherSignupRequest;
import com.equipo2.Appkademy.core.service.RequestService;
import com.equipo2.Appkademy.rest.dto.request.TeacherSignupRequestCreateDto;
import com.equipo2.Appkademy.rest.dto.response.TeacherSignupRequestResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/v1/categories/1/providers/register/")
public class SignupRequestController implements ISignupRequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private AppkademyMapper mapper;

    @PostMapping
    public ResponseEntity<TeacherSignupRequestResponseDto> createSignupRequest(@RequestBody @Valid TeacherSignupRequestCreateDto signupRequestDto){
        TeacherSignupRequest entity = requestService.createSignupRequest(signupRequestDto);
        return new ResponseEntity<TeacherSignupRequestResponseDto>(mapper
                .teacherSignupRequestToTeacherSignupRequestResponseDto(entity), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherSignupRequestResponseDto> getSignupRequestById(@PathVariable Long id){
        TeacherSignupRequest entity = requestService.getSignUpRequestById(id);
        TeacherSignupRequestResponseDto responseDto = mapper.teacherSignupRequestToTeacherSignupRequestResponseDto(entity);
        return ResponseEntity.ok(responseDto);
    }

}
