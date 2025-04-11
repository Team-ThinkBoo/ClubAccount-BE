package com.ClubAccount_BE.user.application.port.out.email;

public interface VerificationCodePort {
    void saveCode(String email, String code);
    String getCode(String email);
}
