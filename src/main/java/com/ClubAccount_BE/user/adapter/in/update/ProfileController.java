package com.ClubAccount_BE.user.adapter.in.update;

import com.ClubAccount_BE.core.meta.LoginUser;
import com.ClubAccount_BE.user.adapter.in.update.dto.request.ProfileUpdateRequest;
import com.ClubAccount_BE.user.application.port.in.update.ProfileUseCase;
import com.ClubAccount_BE.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileUseCase profileUseCase;

    @PatchMapping("/update")
    public void updateProfile(@LoginUser User user,
                              @RequestPart(value = "profileImage", required = false) MultipartFile profileImage,
                              @RequestPart(value = "profile", required = false) ProfileUpdateRequest profileDto) {
        profileUseCase.updateProfile(user, profileImage, profileDto);
    }
}