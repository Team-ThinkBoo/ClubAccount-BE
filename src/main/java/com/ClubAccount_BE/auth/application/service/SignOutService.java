package com.ClubAccount_BE.auth.application.service;

import com.ClubAccount_BE.auth.application.port.in.SignOutUseCase;
import com.ClubAccount_BE.auth.application.port.out.DeleteRefreshTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SignOutService implements SignOutUseCase {

    private final DeleteRefreshTokenPort deleteRefreshTokenPort;

    @Override
    public void logout(String refreshToken) {
        deleteRefreshTokenPort.deleteByRefreshToken(refreshToken);
    }
}