package com.ClubAccount_BE.receipt.application.port.in;

import com.ClubAccount_BE.receipt.adapter.in.web.dto.request.ReceiptRequest;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.CreateReceiptResponseDto;
import com.ClubAccount_BE.user.domain.User;
import org.springframework.web.multipart.MultipartFile;

public interface CreateReceiptUseCase {

    CreateReceiptResponseDto createReceipt(
            User user,
            MultipartFile image,
            ReceiptRequest receiptRequest
    );
}
