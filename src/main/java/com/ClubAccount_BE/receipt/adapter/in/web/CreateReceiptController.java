package com.ClubAccount_BE.receipt.adapter.in.web;

import com.ClubAccount_BE.core.meta.LoginUser;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.request.ReceiptRequest;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.CreateReceiptResponseDto;
import com.ClubAccount_BE.receipt.application.port.in.CreateReceiptUseCase;
import com.ClubAccount_BE.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/receipts")
public class CreateReceiptController implements CreateReceiptApi {

    private final CreateReceiptUseCase createReceiptUseCase;

    @PostMapping("/create")
    public CreateReceiptResponseDto createReceipt(
            @LoginUser User user,
            @RequestPart(value = "image", required = false) MultipartFile image,
            @RequestPart(value = "request") ReceiptRequest receiptRequest
    ) {
        return createReceiptUseCase.createReceipt(user, image, receiptRequest);
    }
}
