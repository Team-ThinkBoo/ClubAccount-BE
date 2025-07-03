package com.ClubAccount_BE.receipt.adapter.in.web.dto.response;

import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.receipt.domain.ReceiptCategory;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;

@Builder
public record ReceiptResponse(
        Long id,
        ReceiptCategory category,
        String businessName,
        LocalDate date,
        BigDecimal amount,
        String etc,
        String receiptImageUrl,
        boolean amountMatched
) {

    public static ReceiptResponse of(Receipt receipt) {
        return ReceiptResponse.builder()
                .id(receipt.getId())
                .category(receipt.getCategory())
                .businessName(receipt.getBusinessName())
                .date(receipt.getDate())
                .amount(receipt.getAmount())
                .etc(receipt.getEtc())
                .receiptImageUrl(receipt.getReceiptImageUrl())
                .amountMatched(receipt.isAmountMatched())
                .build();
    }
}
