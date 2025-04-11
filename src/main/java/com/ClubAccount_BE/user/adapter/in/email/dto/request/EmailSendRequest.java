package com.ClubAccount_BE.user.adapter.in.email.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailSendRequest {
    @NotBlank
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    @Schema(name = "email", example = "thinkboo@example.com")
    private String email;
}