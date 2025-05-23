package com.ClubAccount_BE.user.application.service.delete;

import com.ClubAccount_BE.user.application.port.in.delete.DeleteUserUseCase;
import com.ClubAccount_BE.user.application.port.out.UserPort;
import com.ClubAccount_BE.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class DeleteUserService implements DeleteUserUseCase {

    private final UserPort userPort;

    @Override
    public void deleteUser(User user) {
        userPort.delete(user);
    }
}
