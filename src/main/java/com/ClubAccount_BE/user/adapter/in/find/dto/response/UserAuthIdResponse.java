package com.ClubAccount_BE.user.adapter.in.find.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record UserAuthIdResponse(
        @Schema(name = "authId", example = "thinkboo@example.com")
        String authId
) {

    public static UserAuthIdResponse of(String encryptedAutId) {
        return UserAuthIdResponse.builder()
                .authId(encryptedAutId)
                .build();
    }
}
