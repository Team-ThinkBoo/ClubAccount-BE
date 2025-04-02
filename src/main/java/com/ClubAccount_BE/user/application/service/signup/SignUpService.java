package com.ClubAccount_BE.user.application.service.signup;

import com.ClubAccount_BE.user.application.port.in.signup.SignUpCommand;
import com.ClubAccount_BE.user.application.port.in.signup.SignUpUseCase;
import com.ClubAccount_BE.user.application.port.out.CheckUserPort;
import com.ClubAccount_BE.user.application.port.out.SaveUserPort;
import com.ClubAccount_BE.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ClubAccount_BE.core.exception.ErrorCode.DUPLICATED_AUTHID;

@Service
@Transactional
@RequiredArgsConstructor
public class SignUpService implements SignUpUseCase {
    private final SaveUserPort saveUserPort;
    private final CheckUserPort checkUserPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpCommand signUpCommand) {
        checkDuplicateUser(signUpCommand);
        String encodedPassword = passwordEncoder.encode(signUpCommand.getPassword());
        User newUser = User.create(
                signUpCommand.getAuthId(),
                encodedPassword,
                signUpCommand.getOrganization()
        );
        saveUserPort.saveUser(newUser);
    }
    private void checkDuplicateUser(SignUpCommand signUpCommand) {
        if (checkUserPort.checkDuplicateAuthId(signUpCommand.getAuthId())) {
            throw new IllegalArgumentException(DUPLICATED_AUTHID.toString());
        }
    }
}
