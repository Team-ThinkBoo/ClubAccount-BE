package com.ClubAccount_BE.user.adapter.out.persistence.entity;

import com.ClubAccount_BE.core.entity.TimeBaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;


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

    private String profileUrl;

    @Column(length = 36, nullable = false, unique = true)
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID link;
}
