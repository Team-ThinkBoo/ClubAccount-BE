package com.ClubAccount_BE.auth.adapter.in.web.token;

import com.ClubAccount_BE.auth.adapter.in.web.token.dto.request.TokenRequest;
import com.ClubAccount_BE.auth.adapter.in.web.token.dto.response.AccessTokenResponse;
import com.ClubAccount_BE.auth.application.port.in.TokenUseCase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

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
        return tokenUseCase.createNewToken(tokenRequest.getRefreshToken());
    }
}