package com.ClubAccount_BE.user.adapter.in.signup.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthIdDuplicationResponse {
    @Schema(name = "authId", example = "thinkboo@example.com")
    private final String authId;
    @Schema(name = "notDuplicated", example = "true")
    private final boolean notDuplicated;

    @Builder
    private AuthIdDuplicationResponse(String authId, boolean notDuplicated) {
        this.authId = authId;
        this.notDuplicated = notDuplicated;
    }
}