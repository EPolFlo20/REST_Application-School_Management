package com.example.aws.service.impl;

import java.util.Map;
//import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import com.example.aws.exception.AlumnoException;

@Service
public class AwsS3Service {

    private String bucketName = "aws-project-buket";

    private String region = "us-east-1";

    private String accessKey = "";

    private String secretKey = "";

    private String sessionToken = "";

    @Bean
    public S3Client createS3Client() {
        AwsSessionCredentials sessionCredentials = AwsSessionCredentials.create(
                accessKey,
                secretKey,
                sessionToken);

        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(sessionCredentials))
                .build();
    }

    public String uploadFile(Long alumnoID, MultipartFile file) {
        try {
            S3Client s3Client = createS3Client();
            String objectKey = "alumnos/" + alumnoID + "/" + file.getOriginalFilename();

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(objectKey)
                    //.acl(ObjectCannedACL.PUBLIC_READ)
                    .metadata(Map.of("uploadedBy", "AwsS3Service"))
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
            return "https://" + bucketName + ".s3.amazonaws.com/" + objectKey;
        } catch (Exception e) {
            throw new AlumnoException("Error al subir el archivo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String getExtension(String fileName) {
        if (fileName == null || fileName.isEmpty() || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf('.'));
    }

}
