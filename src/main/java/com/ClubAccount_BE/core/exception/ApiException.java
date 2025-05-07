package com.ClubAccount_BE.core.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private ErrorCode errorCode;

    private String detailMessage;

    public ApiException(ErrorCode e) {
        super(e.getMessage());
        this.errorCode = e;
    }

    public ApiException(ErrorCode errorCode, String detailMessage) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.detailMessage = detailMessage;
    }
}
