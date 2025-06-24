package com.ClubAccount_BE.user.adapter.in.email;

import com.ClubAccount_BE.user.adapter.in.email.dto.request.EmailSendRequest;
import com.ClubAccount_BE.user.adapter.in.email.dto.request.EmailVerifyRequest;
import com.ClubAccount_BE.user.adapter.in.email.dto.response.EmailVerifyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

    @Tag(name = "Email", description = "이메일 인증 관련 API")
    public interface EmailApi {

        @Operation(summary = "이메일 인증 코드 전송")
        ResponseEntity<Void> sendVerificationEmail(@Valid @RequestBody EmailSendRequest request);

        @Operation(summary = "이메일 인증 코드 검증")
        ResponseEntity<EmailVerifyResponse> verifyCode(@Valid @RequestBody EmailVerifyRequest request);
    }
