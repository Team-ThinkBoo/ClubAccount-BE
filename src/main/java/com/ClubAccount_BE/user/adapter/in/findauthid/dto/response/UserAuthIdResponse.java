package com.ClubAccount_BE.user.adapter.in.findauthid.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserAuthIdResponse {

    @Schema(name = "authId", example = "thinkboo@example.com")
    private String authId;


    @Builder
    private UserAuthIdResponse(String authId) {
        this.authId = authId;

    }

    public static UserAuthIdResponse of(String encryptedAutId) {
        return UserAuthIdResponse.builder()
                .authId(encryptedAutId)
                .build();
    }
}
