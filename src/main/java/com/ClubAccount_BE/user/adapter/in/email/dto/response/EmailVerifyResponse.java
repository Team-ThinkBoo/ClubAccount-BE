package com.ClubAccount_BE.user.adapter.in.email.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailVerifyResponse {
    private boolean success;
    private String message;

    public EmailVerifyResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
