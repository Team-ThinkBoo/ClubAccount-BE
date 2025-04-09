package com.ClubAccount_BE.receipt.adapter.out.persistence.repository;

import com.ClubAccount_BE.receipt.adapter.out.persistence.entity.ReceiptEntity;
import com.ClubAccount_BE.user.adapter.out.persistence.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<ReceiptEntity, Long> {

    Page<ReceiptEntity> findAllByUserId(Long id, Pageable pageable);

    @EntityGraph(attributePaths = "receiptItems")
    Optional<ReceiptEntity> findByUserAndId(UserEntity user, Long id);
}
