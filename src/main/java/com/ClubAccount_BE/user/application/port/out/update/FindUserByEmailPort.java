package com.ClubAccount_BE.user.application.port.out.update;

import com.ClubAccount_BE.user.domain.User;

public interface FindUserByEmailPort {
    User findByAuthId(String email);
}
