package com.ClubAccount_BE.auth.security;

import com.ClubAccount_BE.core.exception.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.ClubAccount_BE.core.exception.ErrorCode.AUTHENTICATION_FAIL_FORBIDDEN;

@Component
@RequiredArgsConstructor
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private static final ExceptionResponse E403 = ExceptionResponse.from(
            AUTHENTICATION_FAIL_FORBIDDEN);

    private final ObjectMapper om;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setHeader("content-type", "application/json");
        response.getWriter()
                .write(om.writeValueAsString(E403));
        response.getWriter()
                .flush();
        response.getWriter()
                .close();
    }
}
