package com.ClubAccount_BE.user.adapter.in.signup;

import com.ClubAccount_BE.user.adapter.in.signup.dto.request.SignUpRequest;
import com.ClubAccount_BE.user.adapter.in.signup.dto.response.AuthIdDuplicationResponse;
import com.ClubAccount_BE.user.application.port.in.check.CheckAuthIdDuplicationUseCase;
import com.ClubAccount_BE.user.application.port.in.signup.SignUpUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class SignUpController implements SignUpApiPresentation {
    private final SignUpUseCase signUpUseCase;
    private final CheckAuthIdDuplicationUseCase checkAuthIdDuplicationUseCase;

    @PostMapping("/sign-up")
    public void signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        signUpUseCase.signUp(signUpRequest.toCommand());
    }

    @GetMapping(value = "/sign-up/check-duplicate-auth-id", produces = "application/json")
    public AuthIdDuplicationResponse checkAuthIdDuplication(
            @RequestParam("auth-id") @Size(min = 6, message = "INVALIDATED_AUTHID_TYPE") String authId
    ) {
        return checkAuthIdDuplicationUseCase.checkAuthIdDuplication(authId);
    }
}
