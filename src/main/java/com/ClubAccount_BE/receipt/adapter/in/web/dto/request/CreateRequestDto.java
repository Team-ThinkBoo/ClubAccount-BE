package com.ClubAccount_BE.receipt.adapter.in.web.dto.request;

import com.ClubAccount_BE.receipt.domain.type.ReceiptCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateRequestDto(

        @Schema(
                description = "영수증 분류 카테고리",
                example = "GROUP_DINING(회식비), SUPPLY_PURCHASE(물품 구매비), SUBSCRIPTION(정기 구독비), VENUE_RENTAL(대관비), OTHER(기타)"
        )
        ReceiptCategory category,

        @Schema(description = "영수증 승인 일자", example = "2023-10-01")
        LocalDate date,

        String businessName,

        @Schema(description = "영수증 비용", example = "10000")
        BigDecimal amount,

        @Schema(description = "영수증 비고")
        String etc
) {

}
