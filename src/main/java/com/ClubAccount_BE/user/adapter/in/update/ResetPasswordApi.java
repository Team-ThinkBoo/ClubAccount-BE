package com.ClubAccount_BE.user.adapter.in.update;

import com.ClubAccount_BE.user.adapter.in.update.dto.request.ResetPasswordRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Password Reset", description = "비밀번호 재설정 API")
public interface ResetPasswordApi {
    @Operation(summary = "비밀번호 재설정", description = "비밀번호를 재설정합니다.")
    void resetPassword(ResetPasswordRequest request);
}
