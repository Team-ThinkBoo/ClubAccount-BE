package com.ClubAccount_BE.auth.security;

import com.ClubAccount_BE.core.exception.ApiException;
import com.ClubAccount_BE.core.exception.ErrorCode;
import com.ClubAccount_BE.user.application.port.in.FindUserUseCase;
import com.ClubAccount_BE.user.domain.User;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;

    private final FindUserUseCase findUserUseCase;

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;
        return processAuthentication(authenticationToken);
    }

    private Authentication processAuthentication(JwtAuthenticationToken authenticationToken) {
        User user = findUserUseCase.getUserByAuthId(
                String.valueOf(authenticationToken.getPrincipal()));
        String userPassword = String.valueOf(authenticationToken.getCredentials());
        if (user == null) {
            throw new ApiException(ErrorCode.AUTH_USER_NOT_FOUND);
        }
        if (!user.matchPassword(passwordEncoder, userPassword)) {
            throw new ApiException(ErrorCode.AUTH_INCORRECT_PASSWORD);
        }
        return new JwtAuthenticationToken(
                user.getId(), null, Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
