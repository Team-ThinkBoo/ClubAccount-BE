package com.ClubAccount_BE.user.adapter.in.email;

import com.ClubAccount_BE.user.adapter.in.email.dto.request.EmailSendRequest;
import com.ClubAccount_BE.user.adapter.in.email.dto.request.EmailVerifyRequest;
import com.ClubAccount_BE.user.adapter.in.email.dto.response.EmailVerifyResponse;
import com.ClubAccount_BE.user.application.service.email.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/email")
@RequiredArgsConstructor
public class EmailController implements EmailApiPresentation {

    private final EmailService emailService;

    @PostMapping("/send")
    @Override
    public ResponseEntity<Void> sendVerificationEmail(@RequestBody @Valid EmailSendRequest request) {
        emailService.sendVerificationEmail(request.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/verify", produces = "application/json")
    @Override
    public ResponseEntity<EmailVerifyResponse> verifyCode(@RequestBody @Valid EmailVerifyRequest request) {
            boolean verified = emailService.verifyCode(request.getEmail(), request.getCode());
            return ResponseEntity.ok(new EmailVerifyResponse(verified, verified ? "✅ 인증 성공" : "❌ 인증 실패"));
    }
}