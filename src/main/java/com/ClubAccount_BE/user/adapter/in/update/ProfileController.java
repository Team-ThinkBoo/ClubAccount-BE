package com.ClubAccount_BE.user.adapter.in.update;

import com.ClubAccount_BE.core.meta.LoginUser;
import com.ClubAccount_BE.user.adapter.in.update.dto.request.LoginUserUpdatePassword;
import com.ClubAccount_BE.user.adapter.in.update.dto.request.ProfileUpdateRequest;
import com.ClubAccount_BE.user.application.port.in.update.ProfileUseCase;
import com.ClubAccount_BE.user.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController implements ProfileApi {

    private final ProfileUseCase profileUseCase;

    @PatchMapping("/update")
    public void updateProfile(@LoginUser User user,
                              @RequestPart(value = "profileImage", required = false) MultipartFile profileImage,
                              @RequestPart(value = "profile", required = false) ProfileUpdateRequest profileDto) {
        profileUseCase.updateProfile(user, profileImage, profileDto);

    }

    @PatchMapping("/regenerate-link")
    public void updateLink(@LoginUser User user) {
        profileUseCase.updateLink(user);
    }

    @PatchMapping("/password")
    public void changePassword(@LoginUser User user,
                               @RequestBody @Valid LoginUserUpdatePassword request) {
        profileUseCase.changePassword(user, request.currentPassword(), request.newPassword());
    }
}