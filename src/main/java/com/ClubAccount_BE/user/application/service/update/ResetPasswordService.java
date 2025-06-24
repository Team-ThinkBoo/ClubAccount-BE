package com.ClubAccount_BE.user.application.service.update;

import com.ClubAccount_BE.user.application.port.in.update.ResetPasswordUseCase;
import com.ClubAccount_BE.user.application.port.out.update.FindUserByEmailPort;
import com.ClubAccount_BE.user.application.port.out.update.UpdatePasswordPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ResetPasswordService implements ResetPasswordUseCase {

    private final FindUserByEmailPort findUserByEmailPort;
    private final UpdatePasswordPort updatePasswordPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void resetPassword(String email, String newPassword) {
        var user = findUserByEmailPort.findByAuthId(email);
        var encodedPassword = passwordEncoder.encode(newPassword);
        updatePasswordPort.updatePassword(user, encodedPassword);
    }
}
