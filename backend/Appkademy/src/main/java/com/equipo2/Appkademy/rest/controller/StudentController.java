package com.equipo2.Appkademy.rest.controller;

import com.equipo2.Appkademy.core.mapper.AppkademyMapper;
import com.equipo2.Appkademy.core.model.entity.Student;
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

import org.springframework.beans.factory.annotation.Value;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

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
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender_user;

    @Autowired
    private StudentService studentService;

    @Autowired
    private AppkademyMapper mapper;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getById(@PathVariable Long id){
        Student entity = studentService.getById(id);
        return ResponseEntity.ok(mapper.studentToStudentResponseDto(entity));
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAuthority('" + STUDENT_CREATE + "')")
    public ResponseEntity<StudentResponseDto> create(@RequestBody StudentCreateRequestDto createRequestDto){
        Student entity = studentService.save(createRequestDto);
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            message.setSubject("Bienvenido a Appkademy!");

            MimeMessageHelper helper= new MimeMessageHelper(message, true);

            // TODO: obtener email del usuario.. en student tenemos solo el ID del usuario.. faltan los datos del usuario
            helper.setTo("rcvmartin@yahoo.com.ar");
            helper.setFrom(sender_user); // "appkademy38@gmail.com");

            // TODO: completar el body
            helper.setText("Hola " + entity.getFirstName() + " " +  entity.getLastName() + ", bienvenido!! ");

            javaMailSender.send(message);
        }
        catch (MessagingException e) {
            throw new RuntimeException(e); // TODO: manejar este error.. loguearlo, etc
        }
        catch (Exception ex) {
            // throw new RuntimeException(ex); // TODO: manejar este error.. loguearlo, etc
        }

        return new ResponseEntity<StudentResponseDto>(mapper.studentToStudentResponseDto(entity), HttpStatus.CREATED);
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
        Student entity = studentService.update(id, updateRequestDto);
        return new ResponseEntity<StudentResponseDto>(mapper.studentToStudentResponseDto(entity), HttpStatus.CREATED);
    }

}
