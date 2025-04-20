package com.ClubAccount_BE.auth.application.port.in;

import com.ClubAccount_BE.auth.application.dto.SignInResult;

public interface SignInUseCase {
    SignInResult signIn(String authId, String password);
}
