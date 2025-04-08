package com.ClubAccount_BE.receipt.domain.type;

import java.util.Arrays;
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

    public static ReceiptCategory fromDisplayName(String displayName) {
        return Arrays.stream(values())
                .filter(receiptCategory -> receiptCategory.displayName.equals(displayName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리 분류가 존재하지 않습니다."));
    }
}
