package com.ClubAccount_BE.receipt.application.service;

import com.ClubAccount_BE.receipt.adapter.in.web.dto.request.CreateReceiptRequestDto;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.CreateReceiptResponseDto;
import com.ClubAccount_BE.receipt.application.port.in.CreateReceiptUseCase;
import com.ClubAccount_BE.receipt.application.port.out.CreateReceiptPort;
import com.ClubAccount_BE.receipt.application.port.out.UploadReceiptPort;
import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.receipt.domain.ReceiptItem;
import com.ClubAccount_BE.user.domain.User;
import com.ClubAccount_BE.user.mapper.UserMapper;
import java.util.List;
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
            User user,
            MultipartFile image,
            CreateReceiptRequestDto createReceiptRequestDto
    ) {
        Receipt receipt = Receipt.create(
                UserMapper.toEntity(user),
                createReceiptRequestDto.category(),
                createReceiptRequestDto.categoryName(),
                createReceiptRequestDto.businessName(),
                createReceiptRequestDto.date(),
                createReceiptRequestDto.amount(),
                createReceiptRequestDto.etc(),
                //TODO : 직접 등록시 기본 이미지로 설정 로직 추가
                image == null ? "" : uploadReceiptPort.uploadReceipt(image)
        );

        List<ReceiptItem> receiptItems = toReceiptItems(createReceiptRequestDto, receipt);

        Long receiptId = createReceiptPort.createReceipt(receipt, receiptItems);
        return CreateReceiptResponseDto.of(receiptId, receipt);
    }

    private List<ReceiptItem> toReceiptItems(
            CreateReceiptRequestDto createReceiptRequestDto,
            Receipt receipt
    ) {
        return createReceiptRequestDto.receiptItems().stream()
                .map(receiptItem -> ReceiptItem.create(
                        receipt,
                        receiptItem.name(),
                        receiptItem.price(),
                        receiptItem.totalPrice(),
                        receiptItem.quantity()
                ))
                .toList();
    }
}
