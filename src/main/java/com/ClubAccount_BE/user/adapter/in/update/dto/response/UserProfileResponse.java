package com.ClubAccount_BE.user.adapter.in.update.dto.response;

import com.ClubAccount_BE.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.UUID;

@Schema(description = "사용자 프로필 응답")
public record UserProfileResponse(

        @Schema(description = "조직명", example = "띵부")
        String department,

        @Schema(description = "이메일", example = "thinkboo@example.com")
        String email,

        @Schema(description = "프로필 이미지 URL", example = "https://cdn.example.com/images/profile.jpg")
        String profileUrl,

        @Schema(description = "공유 링크 UUID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
        UUID link,

        @Schema(description = "가입일", example = "2025-05-06T08:24:08.486Z")
        Instant createdAt

) {
    public static UserProfileResponse from(User user) {
        return new UserProfileResponse(
                user.getDepartment(),
                user.getAuthId(),
                user.getProfileUrl(),
                user.getLink(),
                user.getCreatedAt()
        );
    }
}