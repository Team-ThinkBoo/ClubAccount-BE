package com.ClubAccount_BE.user.adapter.in.update.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;

public record ProfileUpdateRequest(

        @Schema(name = "organization", example = "띵부")
        String organization,

        @Email(message = "이메일 형식이 올바르지 않습니다.")
        @Schema(name = "authId", example = "thinkboo@example.com")
        String authId
) {}
