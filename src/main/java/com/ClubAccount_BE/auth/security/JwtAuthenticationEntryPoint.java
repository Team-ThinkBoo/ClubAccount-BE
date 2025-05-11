package com.ClubAccount_BE.auth.security;

import static com.ClubAccount_BE.core.exception.ErrorCode.AUTH_FAIL_UNAUTHORIZED;

import com.ClubAccount_BE.core.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final ErrorResponse E401 = new ErrorResponse(AUTH_FAIL_UNAUTHORIZED);

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
