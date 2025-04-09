package com.ClubAccount_BE.auth.adapter.in.web.token;

import com.ClubAccount_BE.auth.adapter.in.web.token.dto.request.TokenRequest;
import com.ClubAccount_BE.auth.adapter.in.web.token.dto.response.AccessTokenResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Token", description = "토큰 발급 API")
public interface TokenApiPresentation {
    AccessTokenResponse createNewToken(@Valid @RequestBody TokenRequest tokenRequest, HttpServletRequest request, HttpServletResponse response);
}
