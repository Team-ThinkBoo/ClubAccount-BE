package com.ClubAccount_BE.user.adapter.in.update;

import com.ClubAccount_BE.user.adapter.in.update.dto.request.ResetPasswordRequest;
import com.ClubAccount_BE.user.application.port.in.update.ResetPasswordUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class ResetPasswordController implements ResetPasswordApi {

    private final ResetPasswordUseCase resetPasswordUseCase;

    @PostMapping("/reset-password")
    public void resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        resetPasswordUseCase.resetPassword(request.authId(), request.newPassword());
    }

}
