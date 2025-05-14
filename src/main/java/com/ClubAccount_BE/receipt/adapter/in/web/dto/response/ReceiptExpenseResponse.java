package com.ClubAccount_BE.receipt.adapter.in.web.dto.response;

import com.ClubAccount_BE.receipt.domain.DetailExpenseResult;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;

@Builder
public record ReceiptExpenseResponse(
        UUID id,
        int year,
        int month,
        BigDecimal totalExpense
) {

    public static ReceiptExpenseResponse of(DetailExpenseResult result) {
        return ReceiptExpenseResponse.builder()
                .id(UUID.nameUUIDFromBytes((result.getYear() + "-" + result.getMonth()).getBytes()))
                .year(result.getYear())
                .month(result.getMonth())
                .totalExpense(result.getTotalExpense())
                .build();
    }
}
