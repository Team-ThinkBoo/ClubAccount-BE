package com.ClubAccount_BE.receipt.mapper;

import com.ClubAccount_BE.receipt.adapter.out.persistence.entity.ReceiptEntity;
import com.ClubAccount_BE.receipt.adapter.out.persistence.entity.ReceiptItemEntity;
import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.receipt.domain.ReceiptItem;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ReceiptMapper {

    public static ReceiptEntity toEntity(Receipt receipt) {
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

    public static Receipt toDomain(ReceiptEntity receiptEntity) {
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

    public static ReceiptItemEntity toEntity(ReceiptItem receiptItem) {
        return ReceiptItemEntity.builder()
                .id(receiptItem.getId())
                .receipt(toEntity(receiptItem.getReceipt()))
                .name(receiptItem.getName())
                .price(receiptItem.getPrice())
                .totalPrice(receiptItem.getTotalPrice())
                .quantity(receiptItem.getQuantity())
                .build();
    }

    public static void toEntity(
            List<ReceiptItem> items,
            ReceiptEntity receiptEntity
    ) {
        items.stream()
                .map(ReceiptMapper::toEntity)
                .forEach(receiptEntity::addReceiptItem);
    }
}
