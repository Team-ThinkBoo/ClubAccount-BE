package com.ClubAccount_BE.receipt.adapter.in.web.dto.response;

import com.ClubAccount_BE.receipt.domain.ReceiptCategoryExpenseResult;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record ReceiptCategoryExpenseResponse(
        @Schema(description = "회식비 카테고리 총 지출")
        BigDecimal groupDiningExpense,

        @Schema(description = "물품 구매비 카테고리 총 지출")
        BigDecimal supplyPurchaseExpense,

        @Schema(description = "정기 구독비 카테고리 총 지출")
        BigDecimal subscriptionExpense,

        @Schema(description = "대관비 카테고리 총 지출")
        BigDecimal venueRentalExpense,

        @Schema(description = "기타 카테고리 총 지출")
        BigDecimal otherExpense
) {

    public static ReceiptCategoryExpenseResponse of(ReceiptCategoryExpenseResult result) {
        return ReceiptCategoryExpenseResponse.builder()
                .groupDiningExpense(result.getGroupDiningExpense())
                .supplyPurchaseExpense(result.getSupplyPurchaseExpense())
                .subscriptionExpense(result.getSubscriptionExpense())
                .venueRentalExpense(result.getVenueRentalExpense())
                .otherExpense(result.getOtherExpense())
                .build();
    }

}
