package com.ClubAccount_BE.auth.adapter.in.web.token;

import com.ClubAccount_BE.auth.adapter.in.web.token.dto.request.TokenRequest;
import com.ClubAccount_BE.auth.adapter.in.web.token.dto.response.AccessTokenResponse;
import com.ClubAccount_BE.auth.application.port.in.TokenUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class TokenController implements TokenApiPresentation{
    private final TokenUseCase tokenUseCase;

    @PostMapping("/token")
    public AccessTokenResponse createNewToken(@Valid @RequestBody TokenRequest tokenRequest) {
        return tokenUseCase.createNewToken(tokenRequest.getRefreshToken());
    }

}
