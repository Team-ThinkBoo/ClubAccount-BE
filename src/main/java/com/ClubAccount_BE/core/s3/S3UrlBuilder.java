package com.ClubAccount_BE.core.s3;

public interface S3UrlBuilder {
    String toUrl(String key);

    String createObjectName(String objectName);
}
