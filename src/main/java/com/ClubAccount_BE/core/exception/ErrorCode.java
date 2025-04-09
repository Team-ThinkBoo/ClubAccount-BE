package com.ClubAccount_BE.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    AUTHENTICATION_FAIL_FORBIDDEN("AUTH_403_FORBIDDEN", "접근 권한이 없습니다.", HttpStatus.FORBIDDEN),
    AUTHENTICATION_FAIL_UNAUTHORIZED("AUTH_401_UNAUTHORIZED", "인증되지 않은 사용자입니다.", HttpStatus.UNAUTHORIZED),
    INCORRECT_PASSWORD("AUTH_401_INCORRECT_PASSWORD", "비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED),
    DUPLICATED_AUTHID("AUTH_400_DUPLICATED_AUTHID", "이미 존재하는 아이디입니다.", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED("A001", "인증이 필요합니다.", HttpStatus.UNAUTHORIZED);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String toString() {
        return code;
    }
}
