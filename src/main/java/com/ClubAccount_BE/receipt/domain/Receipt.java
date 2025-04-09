package com.ClubAccount_BE.receipt.domain;

import com.ClubAccount_BE.receipt.domain.type.ReceiptCategory;
import com.ClubAccount_BE.user.adapter.out.persistence.entity.UserEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Receipt {

    private final Long id;
    private final UserEntity user;
    private final ReceiptCategory category;
    private final String categoryName;
    private final String businessName;
    private final LocalDate date;
    private final BigDecimal amount;
    private final String etc;
    private final String receiptImageUrl;
    private final List<ReceiptItem> receiptItems = new ArrayList<>();

    @Builder
    private Receipt(
            Long id,
            UserEntity user,
            ReceiptCategory category,
            String categoryName,
            String businessName,
            LocalDate date,
            BigDecimal amount,
            String etc,
            String receiptImageUrl
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
    }

    public static Receipt create(
            UserEntity user,
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
}
