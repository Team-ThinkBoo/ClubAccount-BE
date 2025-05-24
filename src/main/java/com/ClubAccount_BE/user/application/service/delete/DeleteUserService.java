package com.ClubAccount_BE.user.application.service.delete;

import com.ClubAccount_BE.core.exception.ApiException;
import com.ClubAccount_BE.core.exception.ErrorCode;
import com.ClubAccount_BE.receipt.application.port.out.FindReceiptPort;
import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.user.application.port.in.delete.DeleteUserUseCase;
import com.ClubAccount_BE.user.application.port.out.UserPort;

import com.ClubAccount_BE.user.application.port.out.UserImageKeyExtractorPort;
import com.ClubAccount_BE.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Delete;
import software.amazon.awssdk.services.s3.model.DeleteObjectsRequest;
import software.amazon.awssdk.services.s3.model.ObjectIdentifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteUserService implements DeleteUserUseCase {

    private final UserPort userPort;
    private final FindReceiptPort findReceiptPort;
    private final UserImageKeyExtractorPort userImageKeyExtractorPort;
    private final S3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Override
    public void deleteUser(User user) {
        List<String> allImageKeys = new ArrayList<>();

        // 프로필 이미지
        if (user.getProfileUrl() != null) {
            String key = userImageKeyExtractorPort.extract(user.getProfileUrl());
            if (key != null) {
                allImageKeys.add(key);
            }
        }

        // 영수증 이미지
        List<Receipt> receipts = findReceiptPort.getReceiptCategoryList(user);
        List<String> receiptImageKeys = receipts.stream()
                .map(Receipt::getReceiptImageUrl)
                .filter(Objects::nonNull)
                .map(userImageKeyExtractorPort::extract)
                .filter(Objects::nonNull)
                .toList();
        allImageKeys.addAll(receiptImageKeys);

        // S3 이미지 삭제
        if (!allImageKeys.isEmpty()) {
            List<ObjectIdentifier> s3Objects = allImageKeys.stream()
                    .map(key -> ObjectIdentifier.builder().key(key).build())
                    .toList();

            DeleteObjectsRequest request = DeleteObjectsRequest.builder()
                    .bucket(bucketName)
                    .delete(Delete.builder().objects(s3Objects).build())
                    .build();

            try {
                s3Client.deleteObjects(request);
            } catch (Exception e) {
                throw new ApiException(ErrorCode.S3_UPLOAD_FAIL, e.getMessage());
            }
        }

        // 회원 삭제
        userPort.delete(user);
    }
}