package com.ClubAccount_BE.user.application.service.update;

import com.ClubAccount_BE.user.adapter.in.update.dto.request.ProfileUpdateRequest;
import com.ClubAccount_BE.user.application.port.in.update.ProfileUseCase;
import com.ClubAccount_BE.user.application.port.out.UploadProfileImagePort;
import com.ClubAccount_BE.user.application.port.out.UserPort;
import com.ClubAccount_BE.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProfileService implements ProfileUseCase {

    private final UploadProfileImagePort uploadProfileImagePort;
    private final UserPort userPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void updateProfile(User user, MultipartFile profileImage, ProfileUpdateRequest dto) {
        if (dto != null) {
            if (dto.organization() != null) {
                user.updateDepartment(dto.organization());
            }
            if (dto.authId() != null) {
                user.updateAuthId(dto.authId());
            }
        }

        if (profileImage != null && !profileImage.isEmpty()) {
            String url = uploadProfileImagePort.uploadProfileImage(user.getId(), profileImage);
            user.updateProfileUrl(url);
        }

        userPort.save(user);
    }

    @Override
    public void updateLink(User user) {
        user.updateLink();
        userPort.save(user);
    }

    @Override
    public void changePassword(User user, String currentPassword, String newPassword) {
        if (!user.matchPassword(passwordEncoder, currentPassword)) {
            throw new IllegalArgumentException("기존 비밀번호가 일치하지 않습니다.");
        }
        user.updatePassword(passwordEncoder.encode(newPassword));
        userPort.save(user);
    }
}