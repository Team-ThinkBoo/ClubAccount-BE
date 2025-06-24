package com.ClubAccount_BE.user.adapter.in.email.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailSendRequest(
        @NotBlank
        @Email(message = "유효한 이메일 형식이 아닙니다.")
        @Schema(name = "email", example = "thinkboo@example.com")
        String email
) {}