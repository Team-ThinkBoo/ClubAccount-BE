package com.ClubAccount_BE.user.mapper;


import com.ClubAccount_BE.user.adapter.out.persistence.entity.UserEntity;
import com.ClubAccount_BE.user.domain.User;

public class UserMapper {

    public static User toDomain(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .authId(userEntity.getAuthId())
                .password(userEntity.getPassword())
                .department(userEntity.getDepartment())
                .profileUrl(userEntity.getProfileUrl())
                .link(userEntity.getLink())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .build();
    }

    public static UserEntity toEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .authId(user.getAuthId())
                .password(user.getPassword())
                .department(user.getDepartment())
                .profileUrl(user.getProfileUrl())
                .link(user.getLink())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
