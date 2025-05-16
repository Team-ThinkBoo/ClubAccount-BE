package com.ClubAccount_BE.receipt.adapter.in.web.dto.response;

import com.ClubAccount_BE.receipt.domain.ReceiptMonthlyExpenseResult;
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

    public static ReceiptMonthlyExpenseResponse of(ReceiptMonthlyExpenseResult result) {
        return ReceiptMonthlyExpenseResponse.builder()
                .id(UUID.nameUUIDFromBytes((result.getYear() + "-" + result.getMonth()).getBytes()))
                .year(result.getYear())
                .month(result.getMonth())
                .totalExpense(result.getTotalExpense())
                .build();
    }
}
