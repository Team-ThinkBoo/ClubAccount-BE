package com.ClubAccount_BE.receipt.adapter.in.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record ReceiptItemRequest(

        @Schema(description = "영수증 아이템 이름", example = "치킨")
        @NotBlank(message = "아이템 이름은 필수 입력 항목입니다.")
        @Size(max = 30, message = "아이템 이름은 최대 30자까지 입력 가능합니다.")
        String name,

        @Schema(description = "영수증 아이템 가격", example = "10000")
        @NotNull(message = "가격은 필수 입력 항목입니다.")
        @DecimalMin(value = "0.0", inclusive = false, message = "가격은 0보다 커야 합니다.")
        BigDecimal price,

        @Schema(description = "영수증 아이템 총 가격", example = "20000")
        @NotNull(message = "총 가격은 필수 입력 항목입니다.")
        @DecimalMin(value = "0.0", inclusive = false, message = "총 가격은 0보다 커야 합니다.")
        BigDecimal totalPrice,

        @Schema(description = "영수증 아이템 수량", example = "2")
        @Min(value = 1, message = "수량은 1 이상이어야 합니다.")
        int quantity
) {

}
