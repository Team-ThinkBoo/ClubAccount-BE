package com.ClubAccount_BE.user.adapter.out.persistence;

import com.ClubAccount_BE.core.exception.ApiException;
import com.ClubAccount_BE.core.exception.ErrorCode;

import com.ClubAccount_BE.user.application.port.out.FindUserLinkPort;
import com.ClubAccount_BE.user.adapter.out.persistence.entity.UserEntity;
import com.ClubAccount_BE.user.adapter.out.persistence.repository.UserRepository;
import com.ClubAccount_BE.user.application.port.out.CheckUserPort;
import com.ClubAccount_BE.user.application.port.out.FindUserPort;
import com.ClubAccount_BE.user.application.port.out.UserPort;
import com.ClubAccount_BE.user.application.port.out.update.FindUserByEmailPort;
import com.ClubAccount_BE.user.application.port.out.update.UpdatePasswordPort;
import com.ClubAccount_BE.user.domain.User;
import com.ClubAccount_BE.user.mapper.UserMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements FindUserPort, UserPort, CheckUserPort,
        FindUserByEmailPort, UpdatePasswordPort, FindUserLinkPort {

    private final UserRepository userRepository;

    @Override
    public void save(User user) {
        UserEntity entity = UserMapper.toEntity(user);
        UserEntity saved = userRepository.save(entity);
        UserMapper.toDomain(saved);
    }

    @Override
    public void delete(User user) {
        UserEntity entity = UserMapper.toEntity(user);
        userRepository.delete(entity);
    }

    @Override
    public User getUserByAuthId(String authId) {
        return userRepository.getByAuthId(authId)
                .map(UserMapper::toDomain)
                .orElseThrow(() -> new ApiException(ErrorCode.AUTH_USER_NOT_FOUND));
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(UserMapper::toDomain)
                .orElseThrow(() -> new ApiException(ErrorCode.AUTH_USER_NOT_FOUND));
    }

    @Override
    public User getUserByLink(UUID link) {
        return userRepository.findByLink(link)
                .map(UserMapper::toDomain)
                .orElseThrow(() -> new ApiException(ErrorCode.AUTH_USER_NOT_FOUND));
    }

    @Override
    public boolean checkDuplicateAuthId(String authId) {
        return userRepository.existsByAuthId(authId);
    }

    @Override
    public User findByAuthId(String email) {
        return userRepository.findByAuthId(email)
                .map(UserMapper::toDomain)
                .orElseThrow(() -> new ApiException(ErrorCode.AUTH_USER_NOT_FOUND));
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        user.updatePassword(newPassword);
        UserEntity entity = UserMapper.toEntity(user);
        userRepository.save(entity);
    }

    @Override
    public String findLinkByUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(ErrorCode.AUTH_USER_NOT_FOUND))
                .getLink()
                .toString();
    }
}