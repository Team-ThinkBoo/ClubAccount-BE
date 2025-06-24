package com.ClubAccount_BE.receipt.domain;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

//TODO 도메인 로직 변경 예정에 따른 수정 계획 중..
@Getter
public class ReceiptCategoryExpenseResult {

    private final BigDecimal groupDiningExpense;
    private final BigDecimal supplyPurchaseExpense;
    private final BigDecimal subscriptionExpense;
    private final BigDecimal venueRentalExpense;
    private final BigDecimal otherExpense;

    @Builder
    private ReceiptCategoryExpenseResult(
            BigDecimal groupDiningExpense,
            BigDecimal supplyPurchaseExpense,
            BigDecimal subscriptionExpense,
            BigDecimal venueRentalExpense,
            BigDecimal otherExpense
    ) {
        this.groupDiningExpense = groupDiningExpense;
        this.supplyPurchaseExpense = supplyPurchaseExpense;
        this.subscriptionExpense = subscriptionExpense;
        this.venueRentalExpense = venueRentalExpense;
        this.otherExpense = otherExpense;
    }

    public static ReceiptCategoryExpenseResult of(
            BigDecimal groupDiningExpense,
            BigDecimal supplyPurchaseExpense,
            BigDecimal subscriptionExpense,
            BigDecimal venueRentalExpense,
            BigDecimal otherExpense
    ) {
        return ReceiptCategoryExpenseResult.builder()
                .groupDiningExpense(groupDiningExpense)
                .supplyPurchaseExpense(supplyPurchaseExpense)
                .subscriptionExpense(subscriptionExpense)
                .venueRentalExpense(venueRentalExpense)
                .otherExpense(otherExpense)
                .build();
    }
}
