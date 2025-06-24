package com.ClubAccount_BE.core.s3;

import org.springframework.stereotype.Component;

import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Component
public class DefaultS3KeyExtractor implements S3KeyExtractor {
    @Override
    public String extractKey(String url) {
        if (url == null || url.isBlank()) return null;
        try {
            String path = new URL(url).getPath();
            return URLDecoder.decode(path.substring(1), StandardCharsets.UTF_8);
        } catch (Exception e) {
            return null;
        }
    }
}