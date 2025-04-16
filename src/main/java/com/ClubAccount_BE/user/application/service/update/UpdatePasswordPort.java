package com.ClubAccount_BE.user.application.service.update;

import com.ClubAccount_BE.user.domain.User;

public interface UpdatePasswordPort {
    void updatePassword(User user, String encodedPassword);
}
