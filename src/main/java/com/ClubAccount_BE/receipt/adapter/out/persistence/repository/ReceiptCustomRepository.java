package com.ClubAccount_BE.receipt.adapter.out.persistence.repository;

import com.ClubAccount_BE.receipt.adapter.out.persistence.entity.ReceiptEntity;
import com.ClubAccount_BE.receipt.domain.CategoryExpenseResult;
import com.ClubAccount_BE.receipt.domain.MonthlyExpenseResult;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReceiptCustomRepository {

    Page<ReceiptEntity> findByDate(
            Long userId,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    );

    List<MonthlyExpenseResult> calculateExpensesByMonth(Long userId, int year);

    List<CategoryExpenseResult> calculateExpensesByCategory(Long userId);
}
