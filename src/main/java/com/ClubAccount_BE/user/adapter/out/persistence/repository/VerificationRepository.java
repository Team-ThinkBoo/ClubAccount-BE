package com.ClubAccount_BE.user.adapter.out.persistence.repository;

public interface VerificationRepository {
    void saveCode(String email, String code);
    String getCode(String email);
}
