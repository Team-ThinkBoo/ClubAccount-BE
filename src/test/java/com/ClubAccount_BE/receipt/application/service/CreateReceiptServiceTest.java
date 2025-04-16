package com.ClubAccount_BE.receipt.application.service;

import com.ClubAccount_BE.receipt.application.port.out.CreateReceiptPort;
import com.ClubAccount_BE.receipt.application.port.out.UploadReceiptPort;
import com.ClubAccount_BE.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    private User user;

    @BeforeEach
    void setUp() {
        expectedUrl = "https://s3.amazon.com/bucket/test.png";
        expectedId = 1L;

//        user = User.builder()
//                .id(expectedId)
//                .authId("test")
//                .password("testPassword")
//                .department("testDepartment")
//                .profileUrl("testProfileUrl")
//                .rink("testRink")
//                .createdAt(null)
//                .updatedAt(null)
//                .build();
    }

//    @Test
//    @DisplayName("영수증 등록 테스트")
//    void createReceipt() {
//        // given
//        MultipartFile mockFile = ReceiptTestFactory.createMockMultipartFile();
//        CreateReceiptRequestDto requestDto = ReceiptTestFactory.createReceiptRequestDto();
//
//        given(uploadReceiptPort.uploadReceipt(mockFile)).willReturn(expectedUrl);
//        given(createReceiptPort.createReceipt(any(Receipt.class))).willReturn(expectedId);
//
//        // when
//        CreateReceiptResponseDto result = createReceiptService.createReceipt(user, mockFile,
//                requestDto);
//
//        // then
//        assertThat(result.receiptId()).isEqualTo(expectedId);
//        assertThat(result.receiptImageUrl()).isEqualTo(expectedUrl);
//        assertThat(result.businessName()).isEqualTo("김밥천국");
//    }
}