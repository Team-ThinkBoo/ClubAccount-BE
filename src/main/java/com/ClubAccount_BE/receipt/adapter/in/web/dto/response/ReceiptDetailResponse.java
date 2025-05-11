package com.ClubAccount_BE.receipt.adapter.in.web.dto.response;

import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.receipt.domain.type.ReceiptCategory;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;

@Builder
public record ReceiptDetailResponse(
        Long id,
        ReceiptCategory category,
        String businessName,
        LocalDate date,
        BigDecimal amount,
        String etc,
        String receiptImageUrl,
        List<ReceiptItemResponse> receiptItems,
        boolean isAmountMatched
) {

    public static ReceiptDetailResponse of(Receipt receipt) {
        return ReceiptDetailResponse.builder()
                .id(receipt.getId())
                .category(receipt.getCategory())
                .businessName(receipt.getBusinessName())
                .date(receipt.getDate())
                .amount(receipt.getAmount())
                .etc(receipt.getEtc())
                .receiptImageUrl(receipt.getReceiptImageUrl())
                .receiptItems(ReceiptItemResponse.of(receipt.getReceiptItems()))
                .isAmountMatched(receipt.isAmountMatched())
                .build();
    }
}
