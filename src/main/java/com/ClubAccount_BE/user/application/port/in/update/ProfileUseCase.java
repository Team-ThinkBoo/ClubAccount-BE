package com.ClubAccount_BE.user.application.port.in.update;

import com.ClubAccount_BE.user.adapter.in.update.dto.request.ProfileUpdateRequest;
import com.ClubAccount_BE.user.domain.User;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileUseCase {
    //ProfileResponse getProfile(User user);
    void updateProfile(User user, MultipartFile profileImage , ProfileUpdateRequest dto);
    //void changePassword(User user, PasswordChangeRequest dto);
}
