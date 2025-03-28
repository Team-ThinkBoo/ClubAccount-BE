package com.ClubAccount_BE.receipt.adapter.in.web;

import com.ClubAccount_BE.receipt.adapter.in.web.dto.request.CreateRequestDto;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.CreateResponseDto;
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
public class CreateCreateReceiptController implements CreateReceiptApi {

    private final CreateReceiptUseCase createReceiptUseCase;

    @PostMapping("/create")
    public CreateResponseDto createReceipt(
            @RequestPart(value = "image") MultipartFile image,
            @RequestPart(value = "request") CreateRequestDto createRequestDto
    ) {
        return createReceiptUseCase.createReceipt(image, createRequestDto);
    }
}
