package com.ClubAccount_BE.user.adapter.in.signup;

import com.ClubAccount_BE.user.adapter.in.signup.dto.request.SignUpRequest;
import com.ClubAccount_BE.user.adapter.in.signup.dto.response.AuthIdDuplicationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "SignUp", description = "회원가입을 진행하는 API")
public interface SignUpApi {
    @Operation(description = "회원가입을 진행한다.")
    void signUp(@Valid @RequestBody SignUpRequest signUpRequest);

    @Operation(description = "로그인 아이디 중복 여부를 체크한다.")
    @Parameter(name = "auth-id", description = "아이디")
    AuthIdDuplicationResponse checkAuthIdDuplication(
            @RequestParam("auth-id") @Size(min = 6, message = "INVALIDATED_AUTHID_TYPE") String authId);
}
