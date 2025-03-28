package com.ClubAccount_BE.receipt.adapter.in.web;

import com.ClubAccount_BE.receipt.adapter.in.web.dto.request.CreateReceiptRequestDto;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.CreateReceiptResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Upload Receipt", description = "영수증 등록 API")
public interface CreateReceiptApi {

    @Operation(summary = "영수증 등록", description = "파싱된 영수증 정보를 등록한다.")
    CreateReceiptResponseDto createReceipt(
            @RequestPart(value = "image") MultipartFile image,
            @RequestPart(value = "request") CreateReceiptRequestDto createReceiptRequestDto
    );
}
