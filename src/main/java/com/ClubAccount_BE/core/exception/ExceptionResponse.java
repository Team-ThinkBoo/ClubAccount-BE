package com.ClubAccount_BE.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExceptionResponse {
    private final String errorCode;
    private final String message;
    private final HttpStatus httpStatus;

    private ExceptionResponse(String errorCode, String message, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public static ExceptionResponse from(ErrorCode errorCode) {
        return new ExceptionResponse(
                errorCode.getCode(),
                errorCode.getMessage(),
                errorCode.getHttpStatus()
        );
    }
}
