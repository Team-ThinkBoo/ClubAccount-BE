package com.ClubAccount_BE.auth.adapter.out.persistence;

import com.ClubAccount_BE.auth.adapter.out.persistence.repository.InMemoryTokenRepository;
import com.ClubAccount_BE.auth.application.port.out.DeleteRefreshTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InMemoryDeleteRefreshTokenAdapter implements DeleteRefreshTokenPort {

    private final InMemoryTokenRepository tokenRepository;

    @Override
    public void deleteByRefreshToken(String refreshToken) {
        tokenRepository.deleteRefreshToken(refreshToken);
    }
}
