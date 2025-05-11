package com.ClubAccount_BE.auth.adapter.in.web.token;

import com.ClubAccount_BE.core.exception.ApiException;
import com.ClubAccount_BE.core.exception.ErrorCode;

import com.ClubAccount_BE.auth.adapter.in.web.token.dto.response.AccessTokenResponse;
import com.ClubAccount_BE.auth.application.port.in.TokenUseCase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;
import java.time.Duration;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class TokenController implements TokenApiPresentation{

    private final TokenUseCase tokenUseCase;

    @PostMapping("/token")
    public AccessTokenResponse createNewToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        String refreshToken = extractRefreshTokenFromCookies(request);  // 쿠키에서 리프레시 토큰 추출
        AccessTokenResponse accessTokenResponse = tokenUseCase.createNewToken(refreshToken);

        // 새 리프레시 토큰을 쿠키에 설정
        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .path("/")
                .maxAge(Duration.ofDays(7))
                .sameSite("Lax")
                .secure(false)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return accessTokenResponse;  // 새로 발급된 액세스 토큰 반환
    }

    private String extractRefreshTokenFromCookies(HttpServletRequest request) {
        // 쿠키에서 refreshToken 추출
        if (request.getCookies() != null) {
            for (jakarta.servlet.http.Cookie cookie : request.getCookies()) {
                if ("refreshToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        throw new ApiException(ErrorCode.INVALID_REFRESH_TOKEN);
    }
}
