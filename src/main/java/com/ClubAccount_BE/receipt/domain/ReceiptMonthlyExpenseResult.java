package com.ClubAccount_BE.receipt.domain;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

//TODO 도메인 로직 변경 예정에 따른 수정 계획 중..
@Getter
public class ReceiptMonthlyExpenseResult {

    private final int year;
    private final int month;
    private final BigDecimal totalExpense;

    @Builder
    private ReceiptMonthlyExpenseResult(int year, int month, BigDecimal totalExpense) {
        this.year = year;
        this.month = month;
        this.totalExpense = totalExpense;
    }

    public static ReceiptMonthlyExpenseResult of(int year, int month, BigDecimal totalExpense) {
        return ReceiptMonthlyExpenseResult.builder()
                .year(year)
                .month(month)
                .totalExpense(totalExpense)
                .build();
    }
}
