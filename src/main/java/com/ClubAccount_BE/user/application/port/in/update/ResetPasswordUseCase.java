package com.ClubAccount_BE.user.application.port.in.update;

public interface ResetPasswordUseCase {
    void resetPassword(String email, String newPassword);
}
