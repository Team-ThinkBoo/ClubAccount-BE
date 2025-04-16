package com.ClubAccount_BE.auth.application.port.out;

public interface DeleteRefreshTokenPort {
    void deleteByRefreshToken(String refreshToken);
}
