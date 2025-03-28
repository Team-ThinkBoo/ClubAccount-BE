package com.ClubAccount_BE.receipt.application.service;

import com.ClubAccount_BE.receipt.adapter.in.web.dto.request.CreateRequestDto;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.CreateResponseDto;
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
    public CreateResponseDto createReceipt(MultipartFile image, CreateRequestDto createRequestDto) {
        String imageURL = uploadReceiptPort.uploadReceipt(image);

        Receipt receipt = Receipt.create(
                null,
                createRequestDto.category(),
                createRequestDto.businessName(),
                createRequestDto.date(),
                createRequestDto.amount(),
                createRequestDto.etc(),
                imageURL
        );

        Long receiptId = createReceiptPort.createReceipt(receipt);
        return CreateResponseDto.of(receiptId, receipt);
    }
}
