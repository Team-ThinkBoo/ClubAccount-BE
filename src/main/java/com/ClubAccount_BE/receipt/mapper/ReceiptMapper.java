package com.ClubAccount_BE.receipt.mapper;

import com.ClubAccount_BE.receipt.adapter.out.persistence.entity.ReceiptEntity;
import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.user.mapper.UserMapper;

public class ReceiptMapper {

    public static ReceiptEntity toEntity(Receipt receipt) {
        return ReceiptEntity.builder()
                .id(receipt.getId())
                .user(UserMapper.toEntity(receipt.getUser()))
                .category(receipt.getCategory())
                .businessName(receipt.getBusinessName())
                .amount(receipt.getAmount())
                .date(receipt.getDate())
                .etc(receipt.getEtc())
                .receiptImageUrl(receipt.getReceiptImageUrl())
                .amountMatched(receipt.isAmountMatched())
                .build();
    }

    public static Receipt toDomain(ReceiptEntity receiptEntity) {
        return Receipt.builder().id(receiptEntity.getId())
                .user(UserMapper.toDomain(receiptEntity.getUser()))
                .category(receiptEntity.getCategory())
                .businessName(receiptEntity.getBusinessName())
                .amount(receiptEntity.getAmount())
                .date(receiptEntity.getDate())
                .etc(receiptEntity.getEtc())
                .receiptImageUrl(receiptEntity.getReceiptImageUrl())
                .receiptItems(receiptEntity.getReceiptItems()
                        .stream()
                        .map(ReceiptItemMapper::toDomain)
                        .toList()
                )
                .amountMatched(receiptEntity.isAmountMatched())
                .build();
    }
}
