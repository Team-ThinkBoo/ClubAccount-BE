package com.ClubAccount_BE.receipt.adapter.in.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

public record ReceiptItemRequest(

        @Schema(description = "영수증 아이템 이름", example = "치킨")
        String name,

        @Schema(description = "영수증 아이템 가격", example = "10000")
        BigDecimal price,

        @Schema(description = "영수증 아이템 총 가격", example = "20000")
        BigDecimal totalPrice,

        @Schema(description = "영수증 아이템 수량", example = "2")
        int quantity
) {

}
