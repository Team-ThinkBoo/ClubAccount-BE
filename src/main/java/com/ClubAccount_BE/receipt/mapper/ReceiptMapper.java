package com.ClubAccount_BE.receipt.mapper;

import com.ClubAccount_BE.receipt.adapter.out.persistence.entity.ReceiptEntity;
import com.ClubAccount_BE.receipt.domain.Receipt;
import org.springframework.stereotype.Component;

@Component
public class ReceiptMapper {

    public ReceiptEntity mapToJpaEntity(Receipt receipt) {
        return ReceiptEntity.builder()
                .id(receipt.getId())
                .user(receipt.getUser())
                .category(receipt.getCategory().getDisplayName())
                .businessName(receipt.getBusinessName())
                .amount(receipt.getAmount())
                .date(receipt.getDate())
                .etc(receipt.getEtc())
                .receiptImageUrl(receipt.getReceiptImageUrl())
                .build();
    }
}
