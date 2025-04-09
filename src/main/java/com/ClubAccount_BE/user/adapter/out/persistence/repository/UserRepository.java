package com.ClubAccount_BE.user.adapter.out.persistence.repository;

import com.ClubAccount_BE.user.adapter.out.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> getByAuthId(String authId);
    Optional<UserEntity> findById(Long userId);
    boolean existsByAuthId(String authId);

}