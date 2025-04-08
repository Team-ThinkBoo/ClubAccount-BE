package com.ClubAccount_BE.auth.application.port.in;

import com.ClubAccount_BE.auth.adapter.in.web.signin.dto.response.TokenResponse;

public interface SignInUseCase {
    TokenResponse signIn(String authId, String password);
}
