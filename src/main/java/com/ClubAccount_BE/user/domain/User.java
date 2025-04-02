package com.ClubAccount_BE.user.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;

@Getter
@EqualsAndHashCode(of = "id")
public class User {

    private final Long id;
    private final String authId;
    private String password;
    private final String department;
    private String profileUrl;
    private String rink;
    private final Instant createdAt;
    private final Instant updatedAt;

    @Builder
    public User(Long id, String authId, String password, String department, String profileUrl, String rink, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.authId = authId;
        this.password = password;
        this.department = department;
        this.profileUrl = profileUrl;
        this.rink = rink;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static User create(String authId, String encodedPassword, String department) {
        return User.builder()
                .authId(authId)
                .password(encodedPassword)
                .department(department)
                .rink("none") // 기본값 설정
                .profileUrl(null) // 프로필 URL은 일단 null
                .build();
    }

    public boolean matchPassword(PasswordEncoder passwordEncoder, String rawPassword) {
        return passwordEncoder.matches(rawPassword, this.password);
    }
    public void updatePassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    public void updateRink(String rink) {
        this.rink = rink;
    }

    public void updateProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}