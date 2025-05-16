package com.ClubAccount_BE.receipt.domain.service;

import static com.ClubAccount_BE.receipt.domain.type.ReceiptCategory.GROUP_DINING;
import static com.ClubAccount_BE.receipt.domain.type.ReceiptCategory.OTHER;
import static com.ClubAccount_BE.receipt.domain.type.ReceiptCategory.SUBSCRIPTION;
import static com.ClubAccount_BE.receipt.domain.type.ReceiptCategory.SUPPLY_PURCHASE;
import static com.ClubAccount_BE.receipt.domain.type.ReceiptCategory.VENUE_RENTAL;

import com.ClubAccount_BE.receipt.domain.DetailExpenseResult;
import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.receipt.domain.ReceiptCategoryExpenseResult;
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
     * 영수증 카테고리별 지출 계산
     */
    public ReceiptCategoryExpenseResult calculateCategoryExpense(List<Receipt> receipts) {

        Map<ReceiptCategory, BigDecimal> categoryExpense = receipts.stream()
                .collect(Collectors.groupingBy(
                        Receipt::getCategory,
                        Collectors.reducing(BigDecimal.ZERO, Receipt::getAmount, BigDecimal::add)
                ));

        return ReceiptCategoryExpenseResult.of(
                categoryExpense.getOrDefault(GROUP_DINING, BigDecimal.ZERO),
                categoryExpense.getOrDefault(SUPPLY_PURCHASE, BigDecimal.ZERO),
                categoryExpense.getOrDefault(SUBSCRIPTION, BigDecimal.ZERO),
                categoryExpense.getOrDefault(VENUE_RENTAL, BigDecimal.ZERO),
                categoryExpense.getOrDefault(OTHER, BigDecimal.ZERO)
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
}
