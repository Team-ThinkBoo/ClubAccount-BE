package com.ClubAccount_BE.user.application.service.signup;

import static com.ClubAccount_BE.core.exception.ErrorCode.AUTH_INCORRECT_PASSWORD;

import com.ClubAccount_BE.user.application.port.in.signup.SignUpCommand;
import com.ClubAccount_BE.user.application.port.in.signup.SignUpUseCase;
import com.ClubAccount_BE.user.application.port.out.CheckUserPort;
import com.ClubAccount_BE.user.application.port.out.UserPort;
import com.ClubAccount_BE.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SignUpService implements SignUpUseCase {

    private final UserPort userPort;
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
        userPort.save(newUser);
    }

    private void checkDuplicateUser(SignUpCommand signUpCommand) {
        if (checkUserPort.checkDuplicateAuthId(signUpCommand.getAuthId())) {
            throw new IllegalArgumentException(AUTH_INCORRECT_PASSWORD.toString());
        }
    }
}
