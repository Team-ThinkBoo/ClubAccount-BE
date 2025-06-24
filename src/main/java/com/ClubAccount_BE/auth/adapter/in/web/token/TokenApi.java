package com.ClubAccount_BE.auth.adapter.in.web.token;

import com.ClubAccount_BE.auth.adapter.in.web.token.dto.response.AccessTokenResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Tag(name = "Token", description = "토큰 발급 API")
public interface TokenApi {
    AccessTokenResponse createNewToken(HttpServletRequest request, HttpServletResponse response);
}