package com.ClubAccount_BE.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    ACCESS_DENIED_EXCEPTION("0001", "권한이 없습니다.", HttpStatus.UNAUTHORIZED),
    INTERNAL_SERVER_ERROR("0002", "서버 에러 입니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND("0003", "요청하신 페이지를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    METHOD_ARGUMENT_NOT_VALID("0004", "잘못된 파라미터 요청입니다.", HttpStatus.BAD_REQUEST),
    METHOD_NOT_ALLOWED("0006", "허용되지 않는 메소드입니다.", HttpStatus.METHOD_NOT_ALLOWED),
    NOT_READABLE("0007", "JSON 형식에 오류가 있습니다.", HttpStatus.BAD_REQUEST),
    MISSING_REQUEST_PARAMETER("0008", "필수 파라미터가 누락되었습니다.", HttpStatus.BAD_REQUEST),
    DATABASE_EXCEPTION("0009", "데이터베이스 에러 입니다.", HttpStatus.INTERNAL_SERVER_ERROR),

    // 인증 관련 에러 코드
    AUTHENTICATION_FAIL_FORBIDDEN("1001", "접근 권한이 없습니다.", HttpStatus.FORBIDDEN),
    AUTHENTICATION_FAIL_UNAUTHORIZED("1002", "인증되지 않은 사용자입니다.", HttpStatus.UNAUTHORIZED),
    INCORRECT_PASSWORD("1003", "비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED),
    DUPLICATED_AUTHID("1004", "이미 존재하는 아이디입니다.", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED("1005", "인증이 필요합니다.", HttpStatus.UNAUTHORIZED),

    // 영수증 관련 에러 코드
    INVALID_START_DATE_AFTER_END_DATE("2001", "시작일은 종료일보다 빠르거나 같아야 합니다.", HttpStatus.BAD_REQUEST),
    RECEIPT_NOT_FOUND("2002", "해당 영수증을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    // S3 관련 에러 코드
    S3_UPLOAD_FAIL("3001", "S3에 이미지 업로드 에러입니다.", HttpStatus.INTERNAL_SERVER_ERROR);

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
