package com.ClubAccount_BE.receipt.application.service;

import com.ClubAccount_BE.receipt.adapter.in.web.dto.request.CreateReceiptRequestDto;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.CreateReceiptResponseDto;
import com.ClubAccount_BE.receipt.application.port.in.CreateReceiptUseCase;
import com.ClubAccount_BE.receipt.application.port.out.CreateReceiptPort;
import com.ClubAccount_BE.receipt.application.port.out.UploadReceiptPort;
import com.ClubAccount_BE.receipt.domain.Receipt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CreateReceiptService implements CreateReceiptUseCase {

    private final CreateReceiptPort createReceiptPort;

    private final UploadReceiptPort uploadReceiptPort;

    @Override
    public CreateReceiptResponseDto createReceipt(
            MultipartFile image,
            CreateReceiptRequestDto createReceiptRequestDto
    ) {
        Receipt receipt = Receipt.create(
                //TODO : 회원 정보 추가
                null,
                createReceiptRequestDto.category(),
                createReceiptRequestDto.categoryName(),
                createReceiptRequestDto.businessName(),
                createReceiptRequestDto.date(),
                createReceiptRequestDto.amount(),
                createReceiptRequestDto.etc(),
                //TODO : 직접 등록시 기본 이미지로 설정 로직 추가
                image == null ? "" : uploadReceiptPort.uploadReceipt(image)
        );

        Long receiptId = createReceiptPort.createReceipt(receipt);
        return CreateReceiptResponseDto.of(receiptId, receipt);
    }
}
