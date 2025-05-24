package com.ClubAccount_BE.user.adapter.out.persistence;

import com.ClubAccount_BE.user.application.port.out.UserImageKeyExtractorPort;
import org.springframework.stereotype.Component;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Component
public class UserImageKeyExtractorAdapter implements UserImageKeyExtractorPort {
    @Override
    public String extract(String imageUrl) {
        if (imageUrl == null || imageUrl.isBlank()) {
            return null;
        }

        int index = imageUrl.indexOf(".com/");
        if (index != -1 && index + 5 < imageUrl.length()) {
            String key = imageUrl.substring(index + 5);
            return URLDecoder.decode(key, StandardCharsets.UTF_8);
        }

        return imageUrl;
    }
}
