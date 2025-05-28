package com.ClubAccount_BE.user.application.port.out;

import com.ClubAccount_BE.user.domain.User;

public interface UserPort {
    void save(User user);
    void delete(User user);
}
