package com.ClubAccount_BE.receipt.domain;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReceiptItem {

    private final Long id;
    private final Receipt receipt;
    private final String name;
    private final BigDecimal price;
    private final BigDecimal totalPrice;
    private final int quantity;

    @Builder
    private ReceiptItem(
            Long id,
            Receipt receipt,
            String name,
            BigDecimal price,
            BigDecimal totalPrice,
            int quantity
    ) {
        this.id = id;
        this.receipt = receipt;
        this.name = name;
        this.price = price;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
    }

    public static ReceiptItem create(
            Receipt receipt,
            String name,
            BigDecimal price,
            BigDecimal totalPrice,
            int quantity
    ) {
        return ReceiptItem.builder()
                .receipt(receipt)
                .name(name)
                .price(price)
                .totalPrice(totalPrice)
                .quantity(quantity)
                .build();
    }
}
