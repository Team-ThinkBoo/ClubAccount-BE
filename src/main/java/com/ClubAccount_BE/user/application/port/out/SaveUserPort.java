package com.ClubAccount_BE.user.application.port.out;

import com.ClubAccount_BE.user.domain.User;

public interface SaveUserPort {
    User saveUser(User user);
}
