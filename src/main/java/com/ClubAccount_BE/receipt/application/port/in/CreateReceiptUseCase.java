package com.ClubAccount_BE.receipt.application.port.in;

import com.ClubAccount_BE.receipt.adapter.in.web.dto.request.CreateRequestDto;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.CreateResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface CreateReceiptUseCase {

    CreateResponseDto createReceipt(MultipartFile image, CreateRequestDto createRequestDto);
}
