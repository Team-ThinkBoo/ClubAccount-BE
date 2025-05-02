package com.ClubAccount_BE.receipt.domain.service;

import com.ClubAccount_BE.receipt.domain.DetailCategoryResult;
import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.receipt.domain.type.ReceiptCategory;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ReceiptEditor {

    /**
     * 영수증 카테고리 비율 계산
     */
    public DetailCategoryResult calculateCategoryRatio(List<Receipt> receipts) {
        int total = receipts.size();

        if (total == 0) {
            return DetailCategoryResult.of(0f, 0f, 0f, 0f, 0f);
        }

        Map<ReceiptCategory, Long> counts = receipts.stream()
                .collect(Collectors.groupingBy(Receipt::getCategory, Collectors.counting()));

        return DetailCategoryResult.of(
                ratio(counts.get(ReceiptCategory.GROUP_DINING), total),
                ratio(counts.get(ReceiptCategory.SUPPLY_PURCHASE), total),
                ratio(counts.get(ReceiptCategory.SUBSCRIPTION), total),
                ratio(counts.get(ReceiptCategory.VENUE_RENTAL), total),
                ratio(counts.get(ReceiptCategory.OTHER), total)
        );
    }

    private float ratio(Long count, int total) {
        return count == null ? 0f : (count * 100f / total);
    }
}
