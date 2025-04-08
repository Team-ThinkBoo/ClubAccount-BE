package com.ClubAccount_BE.auth.application.port.out;

public interface SaveRefreshTokenPort {
    void saveRefreshToken(String refreshToken, Long userId);
}
