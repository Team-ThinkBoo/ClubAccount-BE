package com.ClubAccount_BE.receipt.adapter.in.web.dto.response;

import com.ClubAccount_BE.receipt.domain.MonthlyExpenseResult;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;

@Builder
public record ReceiptMonthlyExpenseResponse(
        UUID id,
        int year,
        int month,
        BigDecimal totalExpense
) {

    public static ReceiptMonthlyExpenseResponse of(MonthlyExpenseResult result) {
        return ReceiptMonthlyExpenseResponse.builder()
                .id(UUID.nameUUIDFromBytes((result.year() + "-" + result.month()).getBytes()))
                .year(result.year())
                .month(result.month())
                .totalExpense(result.totalExpense())
                .build();
    }
}
