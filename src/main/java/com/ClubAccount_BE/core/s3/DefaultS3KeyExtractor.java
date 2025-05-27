package com.ClubAccount_BE.core.s3;

import org.springframework.stereotype.Component;

import java.net.URL;
import java.net.URLDecoder;
@Component
public class DefaultS3KeyExtractor implements S3KeyExtractor {
    @Override
    public String extractKey(String url) {
        if (url == null || url.isBlank()) return null;
        try {
            String path = new URL(url).getPath(); // /bucket/key.jpg
            return URLDecoder.decode(path.substring(1), "UTF-8"); // "bucket/key.jpg"
        } catch (Exception e) {
            return null;
        }
    }
}