package com.ClubAccount_BE.user.adapter.in.signup.dto.request;

import com.ClubAccount_BE.core.meta.PasswordMatch;
import com.ClubAccount_BE.user.application.port.in.signup.SignUpCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@PasswordMatch
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    @Schema(name = "authId", example = "thinkboo@example.com")
    private String authId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[!@#$%^&*])(?=.*[a-zA-Z0-9]).{8,20}$", message = "INVALIDATED_PASSWORD_TYPE")
    @Schema(name = "password", example = "thinkboo1343!")
    private String password;

    @NotBlank(message = "비밀번호 확인을 입력해주세요.")
    @Pattern(regexp = "^(?=.*[!@#$%^&*])(?=.*[a-zA-Z0-9]).{8,20}$", message = "INVALIDATED_PASSWORD_TYPE")
    @Schema(name = "passwordCheck", example = "thinkboo1343!")
    private String passwordCheck;

    @NotBlank(message = "조직명을 입력해주세요.")
    @Schema(name = "organization", example = "띵부")
    private String organization;

    @Builder
    private SignUpRequest(String authId, String password, String organization) {
        this.authId = authId;
        this.password = password;
        this.organization = organization;
    }

    public SignUpCommand toCommand() {
        return SignUpCommand.builder()
                .authId(authId)
                .password(password)
                .organization(organization)
                .build();
    }
}
