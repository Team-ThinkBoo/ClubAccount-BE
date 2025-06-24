package com.ClubAccount_BE.auth.application.service;

import com.ClubAccount_BE.auth.application.dto.SignInResult;
import com.ClubAccount_BE.user.application.port.out.FindUserLinkPort;
import com.ClubAccount_BE.auth.application.port.out.SaveRefreshTokenPort;
import com.ClubAccount_BE.auth.security.JwtAuthenticationToken;
import com.ClubAccount_BE.auth.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.ClubAccount_BE.auth.application.port.in.SignInUseCase;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SignInService implements SignInUseCase {
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final SaveRefreshTokenPort saveRefreshTokenPort;
    private final FindUserLinkPort findUserLinkPort;

    @Override
    public SignInResult signIn(String authId, String password) {
        Authentication authentication = authenticateCommand(authId, password);
        Long userId = (Long) authentication.getPrincipal();
        String accessToken = tokenProvider.generateToken(userId);
        String refreshToken = tokenProvider.generateRefreshToken();
        saveRefreshTokenPort.saveRefreshToken(refreshToken, userId);
        String link = findUserLinkPort.findLinkByUserId(userId);
        return new SignInResult(accessToken, refreshToken, link);
    }

    private Authentication authenticateCommand(String authId, String password) {
        JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(authId, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }
}
