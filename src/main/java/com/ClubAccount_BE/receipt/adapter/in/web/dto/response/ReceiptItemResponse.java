package com.ClubAccount_BE.receipt.adapter.in.web.dto.response;

import com.ClubAccount_BE.receipt.domain.ReceiptItem;
import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record ReceiptItemResponse(
        Long id,
        String name,
        int quantity,
        BigDecimal price,
        BigDecimal totalPrice
) {

    public static ReceiptItemResponse of(ReceiptItem receiptItem) {
        return ReceiptItemResponse.builder()
                .id(receiptItem.getId())
                .name(receiptItem.getName())
                .quantity(receiptItem.getQuantity())
                .price(receiptItem.getPrice())
                .totalPrice(receiptItem.getTotalPrice())
                .build();
    }
}
