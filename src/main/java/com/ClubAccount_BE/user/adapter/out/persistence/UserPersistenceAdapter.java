package com.ClubAccount_BE.user.adapter.out.persistence;

import com.ClubAccount_BE.user.adapter.out.persistence.entity.UserEntity;
import com.ClubAccount_BE.user.adapter.out.persistence.repository.UserRepository;
import com.ClubAccount_BE.user.application.port.out.CheckUserPort;
import com.ClubAccount_BE.user.application.port.out.FindUserPort;
import com.ClubAccount_BE.user.application.port.out.SaveUserPort;
import com.ClubAccount_BE.user.domain.User;
import com.ClubAccount_BE.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements FindUserPort, SaveUserPort, CheckUserPort {

    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        UserEntity entity = UserMapper.toEntity(user);
        UserEntity saved = userRepository.save(entity);
        return UserMapper.toDomain(saved);
    }

    @Override
    public User getUserByAuthId(String authId) {
        return userRepository.getByAuthId(authId)
                .map(UserMapper::toDomain)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일의 사용자를 찾을 수 없습니다."));
    }

    @Override
    public boolean checkDuplicateAuthId(String authId) {
        return userRepository.existsByAuthId(authId);
    }
}
