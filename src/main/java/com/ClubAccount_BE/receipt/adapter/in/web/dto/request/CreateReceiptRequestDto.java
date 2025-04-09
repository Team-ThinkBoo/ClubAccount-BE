package com.ClubAccount_BE.receipt.adapter.in.web.dto.request;

import com.ClubAccount_BE.receipt.domain.type.ReceiptCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record CreateReceiptRequestDto(

        @Schema(
                description = "영수증 분류 카테고리",
                example = "GROUP_DINING(회식비), SUPPLY_PURCHASE(물품 구매비), SUBSCRIPTION(정기 구독비), VENUE_RENTAL(대관비), OTHER(기타)"
        )
        ReceiptCategory category,

        @Schema(description = "OTHER(기타) - 카테고리 분류 이름", example = "이체비")
        String categoryName,

        @Schema(description = "영수증 승인 일자", example = "2023-10-01")
        LocalDate date,

        String businessName,

        @Schema(description = "영수증 비용", example = "10000")
        BigDecimal amount,

        @Schema(description = "영수증 비고")
        String etc,

        @Schema(description = "영수증 내 아이템 리스트", example = "")
        List<CreateReceiptItemRequestDto> receiptItems
) {

}
