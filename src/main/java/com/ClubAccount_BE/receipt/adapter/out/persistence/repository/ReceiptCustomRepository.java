package com.ClubAccount_BE.receipt.adapter.out.persistence.repository;

import com.ClubAccount_BE.receipt.adapter.out.persistence.entity.ReceiptEntity;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReceiptCustomRepository {

    Page<ReceiptEntity> findAllByDate(
            Long userId,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    );

    List<ReceiptEntity> findByUserIdAndYear(Long userId, int year);
}
