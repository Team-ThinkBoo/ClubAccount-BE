package com.ClubAccount_BE.user.application.port.in;

import com.ClubAccount_BE.user.domain.User;

public interface FindUserUseCase {
    User getUserByAuthId(String email);
}
