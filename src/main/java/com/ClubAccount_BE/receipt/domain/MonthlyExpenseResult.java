package com.ClubAccount_BE.receipt.domain;

import java.math.BigDecimal;
import lombok.Getter;

public record MonthlyExpenseResult(
        int year,
        int month,
        BigDecimal totalExpense
) {

}
