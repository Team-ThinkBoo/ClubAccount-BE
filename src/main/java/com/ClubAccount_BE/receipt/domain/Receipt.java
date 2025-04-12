package com.ClubAccount_BE.receipt.domain;

import com.ClubAccount_BE.receipt.domain.type.ReceiptCategory;
import com.ClubAccount_BE.user.domain.User;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Receipt {

    private final Long id;
    private final User user;
    private final ReceiptCategory category;
    private final String categoryName;
    private final String businessName;
    private final LocalDate date;
    private final BigDecimal amount;
    private final String etc;
    private final String receiptImageUrl;
    private final List<ReceiptItem> receiptItems;

    @Builder
    private Receipt(
            Long id,
            User user,
            ReceiptCategory category,
            String categoryName,
            String businessName,
            LocalDate date,
            BigDecimal amount,
            String etc,
            String receiptImageUrl,
            List<ReceiptItem> receiptItems
    ) {
        this.id = id;
        this.user = user;
        this.category = category;
        this.categoryName = categoryName;
        this.businessName = businessName;
        this.date = date;
        this.amount = amount;
        this.etc = etc;
        this.receiptImageUrl = receiptImageUrl;
        this.receiptItems = receiptItems;
    }

    public static Receipt create(
            User user,
            ReceiptCategory category,
            String categoryName,
            String businessName,
            LocalDate date,
            BigDecimal amount,
            String etc,
            String receiptImageUrl
    ) {
        return Receipt.builder()
                .user(user)
                .category(category)
                .categoryName(categoryName)
                .businessName(businessName)
                .amount(amount)
                .date(date)
                .etc(etc)
                .receiptImageUrl(receiptImageUrl)
                .build();
    }

    public static Receipt update(
            Long receiptId,
            User user,
            ReceiptCategory category,
            String categoryName,
            String businessName,
            LocalDate date,
            BigDecimal amount,
            String etc
    ) {
        return Receipt.builder()
                .id(receiptId)
                .user(user)
                .category(category)
                .categoryName(categoryName)
                .businessName(businessName)
                .amount(amount)
                .date(date)
                .etc(etc)
                .build();
    }
}
