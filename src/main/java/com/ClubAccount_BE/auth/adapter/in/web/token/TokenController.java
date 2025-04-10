package com.ClubAccount_BE.auth.adapter.in.web.token;

import com.ClubAccount_BE.auth.adapter.in.web.token.dto.request.TokenRequest;
import com.ClubAccount_BE.auth.adapter.in.web.token.dto.response.AccessTokenResponse;
import com.ClubAccount_BE.auth.application.port.in.TokenUseCase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;
import java.time.Duration;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class TokenController implements TokenApiPresentation {

    private final TokenUseCase tokenUseCase;

    @PostMapping("/token")
    public AccessTokenResponse createNewToken(
            @Valid @RequestBody TokenRequest tokenRequest,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        AccessTokenResponse accessTokenResponse = tokenUseCase.createNewToken(tokenRequest.getRefreshToken());

        ResponseCookie cookie = ResponseCookie.from("refreshToken", tokenRequest.getRefreshToken())
                .httpOnly(true)
                .path("/")
                .maxAge(Duration.ofDays(7))
                .sameSite("None")
                .secure(false)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return accessTokenResponse;
    }
}