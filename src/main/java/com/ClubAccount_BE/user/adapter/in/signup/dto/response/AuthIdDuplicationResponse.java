package com.ClubAccount_BE.user.adapter.in.signup.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthIdDuplicationResponse(
    @Schema(name = "authId", example = "thinkboo@example.com")
    String authId,

    @Schema(name = "notDuplicated", example = "true")
    boolean notDuplicated
) {}