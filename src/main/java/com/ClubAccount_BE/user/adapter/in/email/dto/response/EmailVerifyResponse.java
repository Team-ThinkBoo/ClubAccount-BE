package com.ClubAccount_BE.user.adapter.in.email.dto.response;

public record EmailVerifyResponse(
    boolean success,
    String message
) {}
