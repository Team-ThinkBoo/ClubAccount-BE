package com.ClubAccount_BE.auth.adapter.in.web.logout;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Tag(name = "Logout", description = "로그아웃 API")
public interface LogoutApi {
    @Operation(summary = "로그아웃", description = "리프레시 토큰 쿠키를 제거하여 로그아웃합니다.")
     void logout(HttpServletRequest request, HttpServletResponse response);
}
