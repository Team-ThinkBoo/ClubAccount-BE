package com.ClubAccount_BE.receipt.domain;

import com.ClubAccount_BE.receipt.domain.type.ReceiptCategory;
import com.ClubAccount_BE.user.infrastructure.adapter.persistence.entity.UserEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Receipt {

    private final Long id;
    private final UserEntity user;
    private final ReceiptCategory category;
    private final String businessName;
    private final LocalDate date;
    private final BigDecimal amount;
    private final String etc;
    private final String receiptImageUrl;

    @Builder
    private Receipt(
            Long id,
            UserEntity user,
            ReceiptCategory category,
            String businessName,
            LocalDate date,
            BigDecimal amount,
            String etc,
            String receiptImageUrl
    ) {
        this.id = id;
        this.user = user;
        this.category = category;
        this.businessName = businessName;
        this.date = date;
        this.amount = amount;
        this.etc = etc;
        this.receiptImageUrl = receiptImageUrl;
    }

    public static Receipt create(
            UserEntity user,
            ReceiptCategory category,
            String businessName,
            LocalDate date,
            BigDecimal amount,
            String etc,
            String receiptImageUrl
    ) {
        return Receipt.builder()
                .user(user)
                .category(category)
                .businessName(businessName)
                .amount(amount)
                .date(date)
                .etc(etc)
                .receiptImageUrl(receiptImageUrl)
                .build();
    }
}
