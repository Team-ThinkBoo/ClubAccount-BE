package com.ClubAccount_BE.user.application.port.in.check;

import com.ClubAccount_BE.user.adapter.in.signup.dto.response.AuthIdDuplicationResponse;

public interface CheckAuthIdUseCase {

    AuthIdDuplicationResponse checkAuthIdDuplication(String authId);
}
