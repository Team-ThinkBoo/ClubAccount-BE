package com.ClubAccount_BE.auth.security;

import static com.ClubAccount_BE.core.exception.ErrorCode.AUTHENTICATION_FAIL_FORBIDDEN;

import com.ClubAccount_BE.core.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private static final ErrorResponse E403 = new ErrorResponse(AUTHENTICATION_FAIL_FORBIDDEN);

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
