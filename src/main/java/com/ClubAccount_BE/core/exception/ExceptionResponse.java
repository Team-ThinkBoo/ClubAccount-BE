package com.ClubAccount_BE.core.exception;

import lombok.Getter;

@Getter
public class ExceptionResponse {
    private final String errorCode;
    private final String message;


    private ExceptionResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public static ExceptionResponse from(ErrorCode errorCode) {
        return new ExceptionResponse(errorCode.getCode(), errorCode.getMessage());
    }
}
