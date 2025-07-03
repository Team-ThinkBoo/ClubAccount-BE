package com.ClubAccount_BE.receipt.domain;

import lombok.Getter;

@Getter
public enum ReceiptCategory {
    GROUP_DINING("회식비"),
    SUPPLY_PURCHASE("물품 구매비"),
    SUBSCRIPTION("정기 구독비"),
    VENUE_RENTAL("대관비"),
    OTHER("기타");

    private final String displayName;

    ReceiptCategory(String displayName) {
        this.displayName = displayName;
    }
}
