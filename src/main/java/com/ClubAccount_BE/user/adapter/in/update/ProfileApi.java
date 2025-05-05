package com.ClubAccount_BE.user.adapter.in.update;

import com.ClubAccount_BE.user.adapter.in.update.dto.request.ProfileUpdateRequest;
import com.ClubAccount_BE.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Profile", description = "프로필 관련 API")
public interface ProfileApi {
    @Operation(summary = "프로필 수정", description = "조직명, 이메일, 프로필 이미지를 선택적으로 수정합니다.")
    void updateProfile(@Valid ProfileUpdateRequest dto, @Parameter(hidden = true)User user);
}
