package com.ClubAccount_BE.auth.application.dto;

public record SignInResult(String accessToken, String refreshToken, String link) {
}
