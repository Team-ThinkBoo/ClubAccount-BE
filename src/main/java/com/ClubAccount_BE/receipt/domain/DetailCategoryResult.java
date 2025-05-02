package com.ClubAccount_BE.receipt.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DetailCategoryResult {

    private final float groupDiningRatio;
    private final float supplyPurchaseRatio;
    private final float subscriptionRatio;
    private final float venueRentalRatio;
    private final float otherRatio;

    @Builder
    private DetailCategoryResult(
            float groupDiningRatio,
            float supplyPurchaseRatio,
            float subscriptionRatio,
            float venueRentalRatio,
            float otherRatio
    ) {
        this.groupDiningRatio = groupDiningRatio;
        this.supplyPurchaseRatio = supplyPurchaseRatio;
        this.subscriptionRatio = subscriptionRatio;
        this.venueRentalRatio = venueRentalRatio;
        this.otherRatio = otherRatio;
    }

    public static DetailCategoryResult of(
            float groupDiningRatio,
            float supplyPurchaseRatio,
            float subscriptionRatio,
            float venueRentalRatio,
            float otherRatio
    ) {
        return DetailCategoryResult.builder()
                .groupDiningRatio(groupDiningRatio)
                .supplyPurchaseRatio(supplyPurchaseRatio)
                .subscriptionRatio(subscriptionRatio)
                .venueRentalRatio(venueRentalRatio)
                .otherRatio(otherRatio)
                .build();
    }
}
