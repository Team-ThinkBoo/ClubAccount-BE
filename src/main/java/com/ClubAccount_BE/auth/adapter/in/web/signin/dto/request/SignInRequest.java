package com.ClubAccount_BE.auth.adapter.in.web.signin.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record SignInRequest (
    @NotBlank(message = "아이디를 입력해주세요.")
    @Schema(name = "authId", example = "thinkboo@example.com")
    String authId,

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Schema(name = "password", example = "thinkboo1343!")
    String password
) {

}

