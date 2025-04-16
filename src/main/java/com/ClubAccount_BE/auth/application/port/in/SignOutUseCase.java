package com.ClubAccount_BE.auth.application.port.in;

public interface SignOutUseCase {
    void logout(String refreshToken);
}
