package com.ClubAccount_BE.receipt.adapter.in.web.dto.response;

import com.ClubAccount_BE.receipt.domain.CategoryExpenseResult;
import com.ClubAccount_BE.receipt.domain.ReceiptCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record ReceiptCategoryExpenseResponse(
        @Schema(description = "영수증 카테고리 종류")
        ReceiptCategory category,

        @Schema(description = "해당 카테고리 총 지출 금액")
        BigDecimal totalExpense
) {

    public static ReceiptCategoryExpenseResponse of(CategoryExpenseResult result) {
        return ReceiptCategoryExpenseResponse.builder()
                .category(result.category())
                .totalExpense(result.totalExpense())
                .build();
    }

}
