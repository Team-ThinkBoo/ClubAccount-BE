package com.ClubAccount_BE.receipt.adapter.in.web.dto.response;

import com.ClubAccount_BE.receipt.domain.ReceiptItem;
import java.util.List;
import lombok.Builder;

@Builder
public record ReceiptItemResponse(
        Long id,
        String name,
        String price,
        String totalPrice,
        int quantity
) {

    public static ReceiptItemResponse of(ReceiptItem receiptItem) {
        return ReceiptItemResponse.builder()
                .id(receiptItem.getId())
                .name(receiptItem.getName())
                .price(receiptItem.getPrice().toString())
                .totalPrice(receiptItem.getTotalPrice().toString())
                .quantity(receiptItem.getQuantity())
                .build();
    }

    public static List<ReceiptItemResponse> of(List<ReceiptItem> items) {
        return items.stream()
                .map(ReceiptItemResponse::of)
                .toList();
    }
}
