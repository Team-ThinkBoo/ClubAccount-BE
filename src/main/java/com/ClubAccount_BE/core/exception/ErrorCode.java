package com.ClubAccount_BE.core.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    AUTHENTICATION_FAIL_FORBIDDEN("AUTH_403_FORBIDDEN", "접근 권한이 없습니다."),
    AUTHENTICATION_FAIL_UNAUTHORIZED("AUTH_401_UNAUTHORIZED", "인증되지 않은 사용자입니다."),
    INCORRECT_PASSWORD("AUTH_401_INCORRECT_PASSWORD", "비밀번호가 일치하지 않습니다."),
    DUPLICATED_AUTHID("AUTH_400_DUPLICATED_AUTHID", "이미 존재하는 아이디입니다."),
    UNAUTHORIZED("A001", "인증이 필요합니다.");
    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
    @Override
    public String toString() {
        return code;
    }
}
