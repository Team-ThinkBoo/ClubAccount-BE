package com.ClubAccount_BE.receipt.application.port.in;

import com.ClubAccount_BE.receipt.adapter.in.web.dto.request.CreateReceiptRequestDto;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.CreateReceiptResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface CreateReceiptUseCase {

    CreateReceiptResponseDto createReceipt(
            MultipartFile image,
            CreateReceiptRequestDto createReceiptRequestDto
    );
}
