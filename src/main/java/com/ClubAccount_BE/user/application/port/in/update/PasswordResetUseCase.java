package com.ClubAccount_BE.user.application.port.in.update;

public interface PasswordResetUseCase {
    void resetPassword(String email, String newPassword);
}
