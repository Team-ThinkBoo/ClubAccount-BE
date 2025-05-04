package com.ClubAccount_BE.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    AUTHENTICATION_FAIL_FORBIDDEN("1001", "접근 권한이 없습니다.", HttpStatus.FORBIDDEN),
    AUTHENTICATION_FAIL_UNAUTHORIZED("1002", "인증되지 않은 사용자입니다.", HttpStatus.UNAUTHORIZED),
    INCORRECT_PASSWORD("1003", "비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED),
    DUPLICATED_AUTHID("1004", "이미 존재하는 아이디입니다.", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED("1005", "인증이 필요합니다.", HttpStatus.UNAUTHORIZED),

    INVALID_START_DATE_AFTER_END_DATE("2001", "시작일은 종료일보다 빠르거나 같아야 합니다.", HttpStatus.BAD_REQUEST);

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
