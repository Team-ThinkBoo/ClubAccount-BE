package com.ClubAccount_BE.receipt.domain;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DetailExpenseResult {

    private final int year;
    private final int month;
    private final BigDecimal totalExpense;

    @Builder
    private DetailExpenseResult(int year, int month, BigDecimal totalExpense) {
        this.year = year;
        this.month = month;
        this.totalExpense = totalExpense;
    }

    public static DetailExpenseResult of(int year, int month, BigDecimal totalExpense) {
        return DetailExpenseResult.builder()
                .year(year)
                .month(month)
                .totalExpense(totalExpense)
                .build();
    }
}
