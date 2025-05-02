package com.ClubAccount_BE.receipt.adapter.in.web.dto.response;

import com.ClubAccount_BE.receipt.domain.DetailCategoryResult;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record ReceiptCategoryResponse(
        @Schema(description = "회식비 비율")
        float groupDiningRatio,

        @Schema(description = "물품 구매비 비율")
        float supplyPurchaseRatio,

        @Schema(description = "정기 구독비 비율")
        float subscriptionRatio,

        @Schema(description = "대관비 비율")
        float venueRentalRatio,

        @Schema(description = "기타 비율")
        float otherRatio
) {

    public static ReceiptCategoryResponse of(DetailCategoryResult result) {
        return ReceiptCategoryResponse.builder()
                .groupDiningRatio(result.getGroupDiningRatio())
                .supplyPurchaseRatio(result.getSupplyPurchaseRatio())
                .subscriptionRatio(result.getSubscriptionRatio())
                .venueRentalRatio(result.getVenueRentalRatio())
                .otherRatio(result.getOtherRatio())
                .build();
    }

}
