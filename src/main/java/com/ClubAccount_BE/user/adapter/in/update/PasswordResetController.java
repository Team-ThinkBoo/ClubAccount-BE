package com.ClubAccount_BE.user.adapter.in.update;

import com.ClubAccount_BE.user.adapter.in.update.dto.request.PasswordResetRequest;
import com.ClubAccount_BE.user.application.port.in.update.PasswordResetUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class PasswordResetController implements PasswordResetApiPresentation {

    private final PasswordResetUseCase passwordResetUseCase;

    @PostMapping("/reset-password")
    public void resetPassword(@Valid @RequestBody PasswordResetRequest request) {
        passwordResetUseCase.resetPassword(request.authId(), request.newPassword(), request.confirmPassword());
    }

}
