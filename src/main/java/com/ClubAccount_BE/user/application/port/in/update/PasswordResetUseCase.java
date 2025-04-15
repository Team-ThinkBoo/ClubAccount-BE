package com.ClubAccount_BE.user.application.port.in.update;

import jakarta.validation.constraints.NotBlank;

public interface PasswordResetUseCase {
    void resetPassword(String email, String newPassword, @NotBlank(message = "비밀번호 확인은 필수입니다.") String s);
}
