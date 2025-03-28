package com.ClubAccount_BE.factory.receipt;

import com.ClubAccount_BE.receipt.adapter.in.web.dto.request.CreateReceiptRequestDto;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.CreateReceiptResponseDto;
import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.receipt.domain.type.ReceiptCategory;
import java.math.BigDecimal;
import java.time.LocalDate;
import jdk.jfr.Description;
import org.springframework.mock.web.MockMultipartFile;

public class ReceiptTestFactory {

    @Description("영수증 등록 Request DTO 생성")
    public static CreateReceiptRequestDto createReceiptRequestDto() {
        return new CreateReceiptRequestDto(
                ReceiptCategory.SUBSCRIPTION,
                LocalDate.of(2025, 3, 28),
                "김밥천국",
                new BigDecimal("12000"),
                "점심 회의"
        );
    }

    @Description("영수증 등록 Response DTO 생성")
    public static CreateReceiptResponseDto createReceiptResponseDto() {
        return new CreateReceiptResponseDto(
                1L,
                ReceiptCategory.SUBSCRIPTION.getDisplayName(),
                LocalDate.of(2025, 3, 28),
                "김밥천국",
                new BigDecimal("12000"),
                "점심 회의",
                "test image url"
        );
    }

    @Description("멀티파트파일 객체 생성")
    public static MockMultipartFile createMockMultipartFile() {
        return new MockMultipartFile(
                "multipartFile",
                "test.png",
                "image/png",
                "test image content".getBytes()
        );
    }

    @Description("영수증 비즈니스 객체 생성")
    public static Receipt createReceipt() {
        return Receipt.builder()
                .id(1L)
                .user(null)
                .category(ReceiptCategory.SUBSCRIPTION)
                .businessName("김밥천국")
                .date(LocalDate.of(2025, 3, 28))
                .amount(new BigDecimal("12000"))
                .etc("점심 회의")
                .receiptImageUrl("test image url")
                .build();
    }
}
