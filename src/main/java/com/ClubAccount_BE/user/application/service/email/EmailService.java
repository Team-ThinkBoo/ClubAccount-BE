package com.ClubAccount_BE.user.application.service.email;

import com.ClubAccount_BE.user.adapter.out.persistence.repository.VerificationRepository;
import com.ClubAccount_BE.user.application.port.in.email.EmailUseCase;
import com.ClubAccount_BE.user.application.port.out.email.VerificationCodePort;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EmailService implements EmailUseCase {

    private final JavaMailSender mailSender;
    private final VerificationCodePort verificationCodePort;

    @Override
    public void sendVerificationEmail(String toEmail) {
        String code = createRandomCode();
        verificationCodePort.saveCode(toEmail, code);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("띵부 인증코드");
        message.setText("인증코드: " + code);
        mailSender.send(message);
    }

    @Override
    public boolean verifyCode(String email, String inputCode) {
        String savedCode = verificationCodePort.getCode(email);
        return inputCode.equals(savedCode);
    }

    private String createRandomCode() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }
}