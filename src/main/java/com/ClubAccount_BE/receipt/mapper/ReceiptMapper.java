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
                .category(receipt.getCategory())
                .categoryName(receipt.getCategory().getDisplayName())
                .businessName(receipt.getBusinessName())
                .amount(receipt.getAmount())
                .date(receipt.getDate())
                .etc(receipt.getEtc())
                .receiptImageUrl(receipt.getReceiptImageUrl())
                .build();
    }

    public Receipt mapToDomainEntity(ReceiptEntity receiptEntity) {
        return Receipt.builder()
                .id(receiptEntity.getId())
                .user(receiptEntity.getUser())
                .category(receiptEntity.getCategory())
                .categoryName(receiptEntity.getCategoryName())
                .businessName(receiptEntity.getBusinessName())
                .amount(receiptEntity.getAmount())
                .date(receiptEntity.getDate())
                .etc(receiptEntity.getEtc())
                .receiptImageUrl(receiptEntity.getReceiptImageUrl())
                .build();
    }
}
