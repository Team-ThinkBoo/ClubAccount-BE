package com.ClubAccount_BE.receipt.domain.service;

import com.ClubAccount_BE.receipt.domain.DetailCategoryResult;
import com.ClubAccount_BE.receipt.domain.DetailExpenseResult;
import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.receipt.domain.ReceiptItem;
import com.ClubAccount_BE.receipt.domain.type.ReceiptCategory;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;

@Service
public class ReceiptEditor {

    /**
     * 영수증 금액과 영수증 아이템 금액 비교
     */
    public boolean checkAmountMatch(Receipt receipt, List<ReceiptItem> items) {
        BigDecimal total = items.stream()
                .map(ReceiptItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return total.compareTo(receipt.getAmount()) == 0;
    }

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

    /**
     * 영수증 월별 지출 계산
     */
    public List<DetailExpenseResult> calculateExpense(List<Receipt> receiptList, int year) {
        Map<Integer, BigDecimal> monthlyExpense = receiptList.stream()
                .collect(Collectors.groupingBy(
                        receipt -> receipt.getDate().getMonthValue(),
                        Collectors.reducing(BigDecimal.ZERO, Receipt::getAmount, BigDecimal::add)
                ));

        return IntStream.rangeClosed(1, 12)
                .mapToObj(month -> DetailExpenseResult.of(
                        year,
                        month,
                        monthlyExpense.getOrDefault(month, BigDecimal.ZERO)))
                .collect(Collectors.toList());
    }

    private float ratio(Long count, int total) {
        return count == null ? 0f : (count * 100f / total);
    }
}
