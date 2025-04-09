package com.ClubAccount_BE.user.application.service;

import com.ClubAccount_BE.user.application.port.in.FindUserUseCase;
import com.ClubAccount_BE.user.application.port.out.FindUserPort;
import com.ClubAccount_BE.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FindUserService implements FindUserUseCase {

    private final FindUserPort findUserPort;

    @Override
    public User getUserById(Long userId) {
        return findUserPort.getUserById(userId);
    }

    @Override
    public User getUserByAuthId(String authId) {
        return findUserPort.getUserByAuthId(authId);
    }
}