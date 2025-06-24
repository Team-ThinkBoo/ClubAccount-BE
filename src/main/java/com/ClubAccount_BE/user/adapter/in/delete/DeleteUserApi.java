package com.ClubAccount_BE.user.adapter.in.delete;

import com.ClubAccount_BE.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User Deletion", description = "회원 탈퇴 API")
public interface DeleteUserApi {
    @Operation(summary = "회원 탈퇴", description = "로그인된 사용자가 자신의 계정을 탈퇴합니다.")
    void deleteMyAccount(User user);
}
