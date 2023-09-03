package com.equipo2.Appkademy.core.service.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.equipo2.Appkademy.core.service.AwsService;
import com.equipo2.Appkademy.rest.dto.response.FileUploadResponseDto;
import com.equipo2.Appkademy.rest.error.BusinessException;
import com.equipo2.Appkademy.rest.error.ErrorCodes;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AwsServiceImpl implements AwsService {

    private String bucketName = "g2c1-img";

    private String accessKey = "AKIAY3PLHSUJB3S24GIE";

    private String secretAccessKey = "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";

    AWSCredentials credentials = new BasicAWSCredentials(
            accessKey,
            secretAccessKey
    );

    AmazonS3 s3client = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.US_EAST_1)
            .build();

    public FileUploadResponseDto uploadFile(MultipartFile file) {
        try {
            assertFileExtension(file.getContentType());
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            PutObjectResult result = s3client.putObject(new PutObjectRequest(bucketName, accessKey, file.getInputStream(), metadata));
            FileUploadResponseDto responseDto = new FileUploadResponseDto();
            responseDto.setUrl("https://" + bucketName + ".s3.amazonaws.com/" + accessKey);
            return responseDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCodes.UNKOWN_ERROR_WHILE_UPLOADING_FILE);
        }
    }

    private void assertFileExtension(String contentType) {
        if(!contentType.equals("image/png") && !contentType.equals("image/jpeg")){
            throw new BusinessException(ErrorCodes.ONLY_JPEG_OR_PNG_FILE_EXTENSION_ALLOWED);
        }
    }

}
