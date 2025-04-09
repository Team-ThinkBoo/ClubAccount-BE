package com.ClubAccount_BE.receipt.mapper;

import com.ClubAccount_BE.receipt.adapter.out.persistence.entity.ReceiptItemEntity;
import com.ClubAccount_BE.receipt.domain.ReceiptItem;

public class ReceiptItemMapper {

    public static ReceiptItem toDomain(ReceiptItemEntity receiptItemEntity) {
        return ReceiptItem.builder()
                .id(receiptItemEntity.getId())
                .name(receiptItemEntity.getName())
                .price(receiptItemEntity.getPrice())
                .totalPrice(receiptItemEntity.getTotalPrice())
                .quantity(receiptItemEntity.getQuantity())
                .build();
    }
}
