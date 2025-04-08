package com.ClubAccount_BE.auth.application.port.out;

import java.util.Optional;

public interface FindRefreshTokenPort {

    Optional<Long> getByRefreshToken(String refreshToken);
}
