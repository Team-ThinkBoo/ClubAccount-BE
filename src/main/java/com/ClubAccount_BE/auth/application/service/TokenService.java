package com.ClubAccount_BE.auth.application.service;

import com.ClubAccount_BE.core.exception.ApiException;

import com.ClubAccount_BE.auth.adapter.in.web.token.dto.response.AccessTokenResponse;
import com.ClubAccount_BE.auth.application.port.in.TokenUseCase;
import com.ClubAccount_BE.auth.application.port.out.FindRefreshTokenPort;
import com.ClubAccount_BE.auth.security.TokenProvider;
import com.ClubAccount_BE.core.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService implements TokenUseCase {

    private final TokenProvider tokenProvider;
    private final FindRefreshTokenPort findRefreshTokenPort;

    @Override
    public AccessTokenResponse createNewToken(String refreshToken) {
        Long userId = findByRefreshToken(refreshToken);
        String accessToken = tokenProvider.generateToken(userId);
        return AccessTokenResponse.from(accessToken);
    }

    private Long findByRefreshToken(String refreshToken) {
        return findRefreshTokenPort.getByRefreshToken(refreshToken)
                .orElseThrow(() -> new ApiException(ErrorCode.INVALID_REFRESH_TOKEN));
    }
}
