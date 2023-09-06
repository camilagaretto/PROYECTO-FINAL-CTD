package com.equipo2.Appkademy.rest.controller;

import com.equipo2.Appkademy.core.service.TeacherService;
import com.equipo2.Appkademy.rest.controller.documentation.ITeacherProviderController;
import com.equipo2.Appkademy.rest.dto.filter.TeacherFilterDto;
import com.equipo2.Appkademy.rest.dto.request.TeacherCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.request.TeacherUpdateRequestDto;
import com.equipo2.Appkademy.rest.dto.response.TeacherResponseDto;
import com.equipo2.Appkademy.rest.dto.response.TeacherSearchResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.equipo2.Appkademy.core.security.model.PermissionConstants.*;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/v1/categories/1/providers/")
public class TeacherController implements ITeacherProviderController {

    @Autowired
    private TeacherService teacherService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponseDto> get(@PathVariable Long id){
        TeacherResponseDto responseDto = teacherService.getById(id);
        return ResponseEntity.ok(responseDto);
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAuthority('" + TEACHER_CREATE + "')")
    public ResponseEntity<TeacherResponseDto> create(@Valid @RequestBody TeacherCreateRequestDto TeacherCreateRequestDto){
        TeacherResponseDto responseDto = teacherService.save(TeacherCreateRequestDto);
        return new ResponseEntity<TeacherResponseDto>(responseDto, HttpStatus.CREATED);
    }

    @Override
    @PostMapping("/search")
    public ResponseEntity<TeacherSearchResponseDto> search(@RequestBody TeacherFilterDto filter){
        TeacherSearchResponseDto searchResponse = teacherService.search(filter);
        return ResponseEntity.ok(searchResponse);
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('" + TEACHER_UPDATE + "')")
    public ResponseEntity<TeacherResponseDto> update(@PathVariable Long id, @RequestBody @Valid TeacherUpdateRequestDto updateRequestDto){
        TeacherResponseDto responseDto = teacherService.update(id, updateRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    /* TODO TO BE IMPLEMENTED
    @PatchMapping("/{id}")
    public ResponseEntity<TeacherResponseDto> patch(@PathVariable Long id,
                                                          @RequestBody TeacherPatchRequestDto patchRequestDto){
        Teacher entity = teacherService.patch(id, patchRequestDto);
        TeacherResponseDto responseDto = mapper.teacherToTeacherResponseDto(entity);
        return ResponseEntity.ok(responseDto);
    }
     */

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('" + TEACHER_DELETE + "')")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        teacherService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
