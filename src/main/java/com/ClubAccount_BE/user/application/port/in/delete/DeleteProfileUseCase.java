package com.ClubAccount_BE.user.application.port.in.delete;

import com.ClubAccount_BE.user.domain.User;

public interface DeleteProfileUseCase {
    void deleteProfile(User user);
}
