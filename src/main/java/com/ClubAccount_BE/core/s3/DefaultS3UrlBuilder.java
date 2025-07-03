package com.ClubAccount_BE.core.s3;

import static com.ClubAccount_BE.core.constant.CommonConstant.IMAGE_KEY_DELIMITER;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DefaultS3UrlBuilder implements S3UrlBuilder {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Override
    public String toUrl(String key) {
        return "https://" + bucket + ".s3." + region + ".amazonaws.com/" + key;
    }

    @Override
    public String createObjectName(String objectName) {
        return UUID.randomUUID() + IMAGE_KEY_DELIMITER + objectName;
    }
}