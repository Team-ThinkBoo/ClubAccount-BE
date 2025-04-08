package com.ClubAccount_BE.user.adapter.out.persistence.entity;

import com.ClubAccount_BE.core.entity.TimeBaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Table(name = "user")
@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class UserEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String authId;

    @Column(nullable = false)
    private String password;

    private String department;

//    @Column(nullable = false)
//    private String role;

    private String profileUrl;

    private String rink;
}
