package com.ClubAccount_BE.user.adapter.out.persistence;

import com.ClubAccount_BE.user.adapter.out.persistence.repository.VerificationRepository;
import com.ClubAccount_BE.user.application.port.out.email.VerificationCodePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class InMemoryVerificationAdapter implements VerificationCodePort {

    private final VerificationRepository verificationRepository;

    @Override
    public void saveCode(String email, String code) {
        verificationRepository.saveCode(email, code);
    }

    @Override
    public String getCode(String email) {
        return verificationRepository.getCode(email);
    }
}
