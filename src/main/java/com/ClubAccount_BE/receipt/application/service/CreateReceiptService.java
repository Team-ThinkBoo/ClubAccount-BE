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
        String imageURL = uploadReceiptPort.uploadReceipt(image);

        Receipt receipt = Receipt.create(
                null,
                createReceiptRequestDto.category(),
                createReceiptRequestDto.businessName(),
                createReceiptRequestDto.date(),
                createReceiptRequestDto.amount(),
                createReceiptRequestDto.etc(),
                imageURL
        );

        Long receiptId = createReceiptPort.createReceipt(receipt);
        return CreateReceiptResponseDto.of(receiptId, receipt);
    }
}
