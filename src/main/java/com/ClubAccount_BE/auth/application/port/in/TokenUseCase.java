package com.ClubAccount_BE.auth.application.port.in;

import com.ClubAccount_BE.auth.adapter.in.web.token.dto.response.AccessTokenResponse;

public interface TokenUseCase {
    AccessTokenResponse createNewToken(String refreshToken);
}
