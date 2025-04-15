package com.ClubAccount_BE.user.application.service.update;

import com.ClubAccount_BE.user.application.port.in.update.PasswordResetUseCase;
import com.ClubAccount_BE.user.application.port.out.update.FindUserByEmailPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PasswordResetService implements PasswordResetUseCase {

    private final FindUserByEmailPort findUserByEmailPort;
    private final UpdatePasswordPort updatePasswordPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void resetPassword(String email, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            throw new IllegalArgumentException("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        var user = findUserByEmailPort.findByAuthId(email);
        var encodedPassword = passwordEncoder.encode(newPassword);
        updatePasswordPort.updatePassword(user, encodedPassword);
    }
}
