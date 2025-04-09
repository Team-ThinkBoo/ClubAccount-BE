package com.ClubAccount_BE.auth.adapter.in.web.signin;

import com.ClubAccount_BE.auth.adapter.in.web.signin.dto.request.SignInRequest;
import com.ClubAccount_BE.auth.adapter.in.web.signin.dto.response.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletResponse;

@Tag(name = "SignIn", description = "로그인 API")
public interface SignInApiPresentation {

    @Operation(summary = "로그인")
    TokenResponse signIn(@Valid @RequestBody SignInRequest signInRequest, HttpServletResponse response);

}
