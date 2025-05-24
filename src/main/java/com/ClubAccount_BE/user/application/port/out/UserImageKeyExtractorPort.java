package com.ClubAccount_BE.user.application.port.out;

/**
 * S3 이미지 URL에서 객체 키(key)를 추출하는 포트
 */
public interface UserImageKeyExtractorPort {
    String extract(String imageUrl);
}
