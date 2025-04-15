package com.ClubAccount_BE.receipt.adapter.in.web.dto.request;

import com.ClubAccount_BE.receipt.domain.type.ReceiptCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ReceiptRequest(

        @Schema(
                description = "영수증 분류 카테고리",
                example = "GROUP_DINING(회식비), SUPPLY_PURCHASE(물품 구매비), SUBSCRIPTION(정기 구독비), VENUE_RENTAL(대관비), OTHER(기타)"
        )
        ReceiptCategory category,

        @Schema(description = "영수증 승인 일자", example = "2023-10-01")
        @NotNull(message = "일자는 필수 입력 항목입니다.")
        @PastOrPresent(message = "일자는 오늘 이전이어야 합니다.")
        LocalDate date,

        @Schema(description = "영수증 가게 이름", example = "홍길동 식당")
        @NotBlank(message = "가게 이름은 필수 입력 항목입니다.")
        @Size(max = 20, message = "가게 이름은 최대 20자까지 입력 가능합니다.")
        String businessName,

        @Schema(description = "영수증 총 비용", example = "10000")
        @NotNull(message = "비용은 필수 입력 항목입니다.")
        @DecimalMin(value = "0.0", inclusive = false, message = "비용은 0원보다 커야 합니다.")
        BigDecimal amount,

        @Schema(description = "영수증 비고")
        String etc,

        @Schema(description = "영수증 내 아이템 리스트")
        List<ReceiptItemRequest> receiptItems
) {

}
