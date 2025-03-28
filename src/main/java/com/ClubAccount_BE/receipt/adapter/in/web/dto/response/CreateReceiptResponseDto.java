package com.ClubAccount_BE.receipt.adapter.in.web.dto.response;

import com.ClubAccount_BE.receipt.domain.Receipt;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;

@Builder
public record CreateReceiptResponseDto(
        Long receiptId,
        String category,
        LocalDate date,
        String businessName,
        BigDecimal amount,
        String etc,
        String receiptImageUrl
) {

    public static CreateReceiptResponseDto of(Long receiptId, Receipt receipt) {
        return CreateReceiptResponseDto.builder()
                .receiptId(receiptId)
                .category(receipt.getCategory().getDisplayName())
                .date(receipt.getDate())
                .businessName(receipt.getBusinessName())
                .amount(receipt.getAmount())
                .etc(receipt.getEtc())
                .receiptImageUrl(receipt.getReceiptImageUrl())
                .build();
    }
}
