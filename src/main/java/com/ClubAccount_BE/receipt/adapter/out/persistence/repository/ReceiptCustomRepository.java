package com.ClubAccount_BE.receipt.adapter.out.persistence.repository;

import com.ClubAccount_BE.receipt.adapter.out.persistence.entity.ReceiptEntity;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReceiptCustomRepository {

    Page<ReceiptEntity> findAllByDate(
            Long userId,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    );
}
