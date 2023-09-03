package com.equipo2.Appkademy.core.service;

import com.equipo2.Appkademy.rest.dto.response.FileUploadResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface AwsService {

    FileUploadResponseDto uploadFile(MultipartFile file);
}
