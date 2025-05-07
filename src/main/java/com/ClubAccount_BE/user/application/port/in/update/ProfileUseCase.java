package com.ClubAccount_BE.user.application.port.in.update;

import com.ClubAccount_BE.user.adapter.in.update.dto.request.ProfileUpdateRequest;
import com.ClubAccount_BE.user.adapter.in.update.dto.response.UserProfileResponse;
import com.ClubAccount_BE.user.domain.User;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileUseCase {
    void updateProfile(User user, MultipartFile profileImage , ProfileUpdateRequest dto);

    void updateLink(User user);

    void changePassword(User user, String currentPassword, String newPassword);

    UserProfileResponse getProfile(User user);
}
