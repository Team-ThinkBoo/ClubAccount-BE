package com.ClubAccount_BE.auth.security;

import com.ClubAccount_BE.core.exception.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.ClubAccount_BE.core.exception.ErrorCode.AUTHENTICATION_FAIL_UNAUTHORIZED;

@Component
    @RequiredArgsConstructor
    public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

        private static final ExceptionResponse E401 = ExceptionResponse.from(
                AUTHENTICATION_FAIL_UNAUTHORIZED);

        private final ObjectMapper om;

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response,
                             AuthenticationException authException) throws IOException {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("content-type", "application/json");
            response.getWriter()
                    .write(om.writeValueAsString(E401));
            response.getWriter()
                    .flush();
            response.getWriter()
                    .close();
        }
}
