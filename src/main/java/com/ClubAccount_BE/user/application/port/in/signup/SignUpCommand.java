package com.ClubAccount_BE.user.application.port.in.signup;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpCommand {
    private String authId;
    private String password;
    private String organization;

    @Builder
    public SignUpCommand(String authId, String password, String organization) {
        this.authId = authId;
        this.password = password;
        this.organization = organization;
    }
}
