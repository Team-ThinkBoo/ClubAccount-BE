package com.ClubAccount_BE.auth.adapter.in.web.token.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
public record AccessTokenResponse (
        @Schema(name = "accessToken", example = "eyJJV_adQasvds....")
        String accessToken
) {

    public static AccessTokenResponse from(String accessToken) {
        return AccessTokenResponse
                .builder()
                .accessToken(accessToken)
                .build();
    }
}
