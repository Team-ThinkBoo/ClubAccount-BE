package com.ClubAccount_BE.core.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorResponse {

    @Schema(description = "시간", example = "2023-11-05T22:13:54.003779")
    private final LocalDateTime timestamp = LocalDateTime.now();

    @Schema(description = "상태", example = "400")
    private int status;

    @Schema(description = "코드", example = "1402")
    private String code;

    @Schema(description = "메세지", example = "로그인에 실패하셨습니다. 이메일이나 비밀번호를 확인해주세요.")
    private String message;

    @Schema(description = "요청 URL", example = "/api/v1/members")
    private String url;

    @Schema(description = "요청 Method", example = "POST")
    private String method;

    public ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getHttpStatus().value();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public ErrorResponse(int status, String code, String message, String url, String method) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.url = url;
        this.method = method;
    }

    public ErrorResponse(ErrorCode errorCode, String url, String method) {
        this.status = errorCode.getHttpStatus().value();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.url = url;
        this.method = method;
    }
}
