package com.ClubAccount_BE.user.adapter.in.signup;

import com.ClubAccount_BE.user.adapter.in.signup.dto.request.SignUpRequest;
import com.ClubAccount_BE.user.adapter.in.signup.dto.response.AuthIdDuplicationResponse;
import com.ClubAccount_BE.user.application.port.in.check.CheckAuthIdUseCase;
import com.ClubAccount_BE.user.application.port.in.signup.SignUpUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class SignUpController implements SignUpApi {
    private final SignUpUseCase signUpUseCase;
    private final CheckAuthIdUseCase checkAuthIdDuplicationUseCase;

    @PostMapping("/sign-up")
    public void signUp(@Valid @RequestBody SignUpRequest request) {
        signUpUseCase.signUp(request.toCommand());
    }

    @GetMapping(value = "/sign-up/check-duplicate-auth-id", produces = "application/json")
    public AuthIdDuplicationResponse checkAuthIdDuplication(
             String authId
    ) {
        return checkAuthIdDuplicationUseCase.checkAuthIdDuplication(authId);
    }
}
