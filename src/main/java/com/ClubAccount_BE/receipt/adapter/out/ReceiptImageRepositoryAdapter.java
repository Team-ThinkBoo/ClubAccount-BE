package com.ClubAccount_BE.receipt.adapter.out;


import static com.ClubAccount_BE.core.constant.CommonConstant.IMAGE_KEY_DELIMITER;

import com.ClubAccount_BE.receipt.application.port.out.UploadReceiptPort;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
@RequiredArgsConstructor
public class ReceiptImageRepositoryAdapter implements UploadReceiptPort {

    private final S3Client amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public String uploadReceipt(MultipartFile image) {
        String imageName = createImageName(image.getOriginalFilename());

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(imageName)
                    .contentType(image.getContentType())
                    .build();

            amazonS3.putObject(
                    putObjectRequest,
                    RequestBody.fromInputStream(image.getInputStream(), image.getSize())
            );

        } catch (IOException e) {
            throw new RuntimeException("S3 업로드 실패", e);
        }

        return getImageUrl(imageName);
    }

    private String createImageName(String originalFilename) {
        return UUID.randomUUID() + IMAGE_KEY_DELIMITER + originalFilename;
    }

    private String getImageUrl(String fileName) {
        return amazonS3.utilities()
                .getUrl(builder -> builder.bucket(bucket).key(fileName))
                .toExternalForm();
    }
}
