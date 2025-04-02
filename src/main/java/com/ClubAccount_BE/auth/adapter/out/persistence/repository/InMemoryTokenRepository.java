package com.ClubAccount_BE.auth.adapter.out.persistence.repository;

import com.ClubAccount_BE.auth.application.port.out.FindRefreshTokenPort;
import com.ClubAccount_BE.auth.application.port.out.SaveRefreshTokenPort;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class InMemoryTokenRepository implements FindRefreshTokenPort, SaveRefreshTokenPort {

    private static final Cache<String, Long> TOKEN_REPOSITORY = CacheBuilder.newBuilder()
            .expireAfterWrite(15, TimeUnit.DAYS)
            .build();

    @Override
    public void saveRefreshToken(String refreshToken, Long userId) {
        TOKEN_REPOSITORY.put(refreshToken, userId);
    }

    @Override
    public Optional<Long> getByRefreshToken(String refreshToken) {
        return Optional.ofNullable(TOKEN_REPOSITORY.getIfPresent(refreshToken));
    }
}
