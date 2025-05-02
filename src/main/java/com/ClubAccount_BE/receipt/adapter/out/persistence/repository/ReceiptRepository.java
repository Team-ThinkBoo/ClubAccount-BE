package com.ClubAccount_BE.receipt.adapter.out.persistence.repository;

import com.ClubAccount_BE.receipt.adapter.out.persistence.entity.ReceiptEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<ReceiptEntity, Long> {

    Page<ReceiptEntity> findAllByUserId(Long id, Pageable pageable);

    List<ReceiptEntity> findAllByUserId(Long id);

    @EntityGraph(attributePaths = "receiptItems")
    Optional<ReceiptEntity> findReceiptById(Long receiptId);
}
