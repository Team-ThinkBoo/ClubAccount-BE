package com.ClubAccount_BE.receipt.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.ClubAccount_BE.factory.receipt.ReceiptTestFactory;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.request.CreateReceiptRequestDto;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.CreateReceiptResponseDto;
import com.ClubAccount_BE.receipt.application.port.out.CreateReceiptPort;
import com.ClubAccount_BE.receipt.application.port.out.UploadReceiptPort;
import com.ClubAccount_BE.receipt.domain.Receipt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(MockitoExtension.class)
class CreateReceiptServiceTest {

    @Mock
    private CreateReceiptPort createReceiptPort;

    @Mock
    private UploadReceiptPort uploadReceiptPort;

    @InjectMocks
    private CreateReceiptService createReceiptService;

    private String expectedUrl;

    private Long expectedId;

    @BeforeEach
    void setUp() {
        expectedUrl = "https://s3.amazon.com/bucket/test.png";
        expectedId = 1L;
    }

    @Test
    @DisplayName("영수증 등록 테스트")
    void createReceipt() {
        // given
        MultipartFile mockFile = ReceiptTestFactory.createMockMultipartFile();
        CreateReceiptRequestDto requestDto = ReceiptTestFactory.createReceiptRequestDto();

        given(uploadReceiptPort.uploadReceipt(mockFile)).willReturn(expectedUrl);
        given(createReceiptPort.createReceipt(any(Receipt.class))).willReturn(expectedId);

        // when
        CreateReceiptResponseDto result = createReceiptService.createReceipt(mockFile, requestDto);

        // then
        assertThat(result.receiptId()).isEqualTo(expectedId);
        assertThat(result.receiptImageUrl()).isEqualTo(expectedUrl);
        assertThat(result.businessName()).isEqualTo("김밥천국");
    }
}