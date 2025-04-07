package com.ClubAccount_BE.receipt.adapter.in.web.dto.response;

import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.receipt.domain.type.ReceiptCategory;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;

@Builder
public record FindReceiptResponseDto(
        ReceiptCategory category,
        String categoryName,
        String businessName,
        LocalDate date,
        BigDecimal amount,
        String etc,
        String receiptImageUrl
) {

    public static FindReceiptResponseDto of(Receipt receipt) {
        return FindReceiptResponseDto.builder()
                .category(receipt.getCategory())
                .categoryName(receipt.getCategoryName())
                .businessName(receipt.getBusinessName())
                .date(receipt.getDate())
                .amount(receipt.getAmount())
                .etc(receipt.getEtc())
                .receiptImageUrl(receipt.getReceiptImageUrl())
                .build();
    }
}
