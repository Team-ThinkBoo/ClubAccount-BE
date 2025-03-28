package com.ClubAccount_BE.receipt.adapter.in.web;

import com.ClubAccount_BE.receipt.adapter.in.web.dto.request.CreateReceiptRequestDto;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.CreateReceiptResponseDto;
import com.ClubAccount_BE.receipt.application.port.in.CreateReceiptUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/receipts")
public class CreateReceiptController implements CreateReceiptApi {

    private final CreateReceiptUseCase createReceiptUseCase;

    @PostMapping("/create")
    public CreateReceiptResponseDto createReceipt(
            @RequestPart(value = "image") MultipartFile image,
            @RequestPart(value = "request") CreateReceiptRequestDto createReceiptRequestDto
    ) {
        return createReceiptUseCase.createReceipt(image, createReceiptRequestDto);
    }
}
