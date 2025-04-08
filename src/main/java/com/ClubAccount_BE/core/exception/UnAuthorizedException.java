package com.ClubAccount_BE.core.exception;

import lombok.Getter;

@Getter
public class UnAuthorizedException extends RuntimeException{
    private final ErrorCode errorCode;

    public UnAuthorizedException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
