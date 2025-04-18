package com.ClubAccount_BE.user.application.port.out;

import com.ClubAccount_BE.user.domain.User;
import java.util.UUID;

public interface FindUserPort {

    User getUserByAuthId(String email);

    User getUserById(Long userId);

    User getUserByLink(UUID link);
}
