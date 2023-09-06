package com.equipo2.Appkademy.rest.controller;

import com.equipo2.Appkademy.core.service.AwsService;
import com.equipo2.Appkademy.rest.controller.documentation.IFileUploadController;
import com.equipo2.Appkademy.rest.dto.response.FileUploadResponseDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.equipo2.Appkademy.core.security.model.PermissionConstants.FILE_UPLOAD;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/files")
public class FileUploadController implements IFileUploadController {

    @Autowired
    private AwsService awsService;

    @Override
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    @PreAuthorize("hasAuthority('" + FILE_UPLOAD + "')")
    public ResponseEntity<FileUploadResponseDto> uploadToS3(@RequestBody MultipartFile file) {
        FileUploadResponseDto responseDto = awsService.uploadFile(file);
        return new ResponseEntity<FileUploadResponseDto>(responseDto, HttpStatus.CREATED);
    }

}
