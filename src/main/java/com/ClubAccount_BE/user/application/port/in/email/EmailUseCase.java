package com.ClubAccount_BE.user.application.port.in.email;

public interface EmailUseCase {
    void sendVerificationEmail(String email);
    boolean verifyCode(String email, String inputCode);
}
