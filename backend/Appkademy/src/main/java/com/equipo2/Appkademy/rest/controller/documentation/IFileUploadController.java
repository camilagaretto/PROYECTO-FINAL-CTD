package com.equipo2.Appkademy.rest.controller.documentation;

import com.equipo2.Appkademy.rest.dto.response.FileUploadResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "S3 Upload")
public interface IFileUploadController {

    ResponseEntity<FileUploadResponseDto> uploadToS3(MultipartFile file);

}
