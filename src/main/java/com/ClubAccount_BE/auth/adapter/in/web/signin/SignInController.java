package com.ClubAccount_BE.auth.adapter.in.web.signin;

import com.ClubAccount_BE.auth.adapter.in.web.signin.dto.request.SignInRequest;
import com.ClubAccount_BE.auth.adapter.in.web.signin.dto.response.TokenResponse;
import com.ClubAccount_BE.auth.application.port.in.SignInUseCase;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import java.time.Duration;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class SignInController implements SignInApi {

    private final SignInUseCase signInUseCase;

    @PostMapping("/sign-in")
    public TokenResponse signIn(@Valid @RequestBody SignInRequest request, HttpServletResponse response) {
        var signInResult = signInUseCase.signIn(request.authId(), request.password());

        ResponseCookie cookie = ResponseCookie.from("refreshToken", signInResult.refreshToken())
                .httpOnly(true)
                .path("/")
                .maxAge(Duration.ofDays(7))
                .sameSite("Lax")
                .secure(false)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return TokenResponse.of(signInResult.accessToken(), signInResult.link());
    }
}
