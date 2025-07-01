package com.ClubAccount_BE.user.adapter.out.persistence;

import com.ClubAccount_BE.core.exception.ApiException;
import com.ClubAccount_BE.core.exception.ErrorCode;
import com.ClubAccount_BE.core.s3.S3KeyExtractor;
import com.ClubAccount_BE.core.s3.S3UrlBuilder;
import com.ClubAccount_BE.user.application.port.out.UploadProfileImagePort;
import com.ClubAccount_BE.user.application.port.out.delete.DeleteProfileImagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

import static com.ClubAccount_BE.core.constant.CommonConstant.IMAGE_KEY_DELIMITER;

@Component
@RequiredArgsConstructor
public class ProfileImageAdapter implements UploadProfileImagePort, DeleteProfileImagePort {

    private final S3Client amazonS3;
    private final S3KeyExtractor keyExtractor;
    private final S3UrlBuilder urlBuilder;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public String uploadProfileImage(Long userId, MultipartFile image) {
        String imageName = createImageName(image.getOriginalFilename());
        try {
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(imageName)
                    .contentType(image.getContentType())
                    .build();
            amazonS3.putObject(request,
                    RequestBody.fromInputStream(image.getInputStream(), image.getSize())
            );
        } catch (IOException e) {
            throw new ApiException(ErrorCode.S3_UPLOAD_FAIL, e.getMessage());
        }
        return urlBuilder.toUrl(imageName);
    }

    private String createImageName(String originalFilename) {
        return UUID.randomUUID() + IMAGE_KEY_DELIMITER + originalFilename;
    }

    @Override
    public void deleteImages(String profileImage) {
        try {
            String key = keyExtractor.extractKey(profileImage);
            amazonS3.deleteObject(builder -> builder
                    .bucket(bucket)
                    .key(key)
            );
        } catch (Exception e) {
            throw new ApiException(ErrorCode.S3_DELETE_FAIL);
        }
    }
}