package com.ClubAccount_BE.user.adapter.in.update;

import com.ClubAccount_BE.user.adapter.in.update.dto.request.ProfileUpdateRequest;
import com.ClubAccount_BE.user.adapter.in.update.dto.request.LoginUserUpdatePassword;
import com.ClubAccount_BE.user.adapter.in.update.dto.response.UserProfileResponse;
import com.ClubAccount_BE.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Profile", description = "프로필 관련 API")
public interface ProfileApi {

    @Operation(summary = "프로필 수정", description = "조직명, 이메일, 프로필 이미지를 선택적으로 수정합니다.")
    void updateProfile(
            @Parameter(hidden = true) User user,
            @RequestPart(value = "profileImage", required = false) MultipartFile profileImage,
            @RequestPart(value = "profile", required = false) @Valid ProfileUpdateRequest dto
    );

    @Operation(summary = "사용자 링크 재생성", description = "회원 UUID 기반 사용자 링크를 새로 발급합니다.")
    void updateLink(
            @Parameter(hidden = true) User user
    );

    @Operation(summary = "비밀번호 변경", description = "기존 비밀번호를 확인하고 새로운 비밀번호로 변경합니다.")
    void changePassword(
            @Parameter(hidden = true) User user,
            @RequestBody @Valid LoginUserUpdatePassword request
    );

    @Operation(summary = "프로필 조회", description = "현재 로그인된 사용자의 프로필 정보를 조회합니다.")
    @GetMapping
    UserProfileResponse getProfile(
            @Parameter(hidden = true) User user
    );
}