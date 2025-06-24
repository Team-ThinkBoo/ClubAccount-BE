package com.ClubAccount_BE.user.adapter.in.update.dto.request;

import com.ClubAccount_BE.core.meta.PasswordMatch;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@PasswordMatch
public record PasswordResetRequest(
        @NotBlank @Email
        @Schema(name = "authId", example = "thinkboo@example.com")
        String authId,

        @NotBlank(message = "새 비밀번호는 필수입니다.")
        @Schema(name = "newPassword", example = "thinkboo1343!")
        String newPassword,

        @NotBlank(message = "비밀번호 확인은 필수입니다.")
        @Schema(name = "confirmPassword", example = "thinkboo1343!")
        String confirmPassword
) {
        
}
