package com.ClubAccount_BE.auth.adapter.in.web.signin.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
public record TokenResponse (
    @Schema(name = "accessToken", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")
    String accessToken,

    @Schema(name = "link", example = "1ecabfde-8574-4c98-a35a-4919c23e1c9f")
    String link
) {
    public static TokenResponse of(String accessToken, String link) {
        return TokenResponse.builder()
                .accessToken(accessToken)
                .link(link)
                .build();
    }
}
