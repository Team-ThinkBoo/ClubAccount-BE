package com.ClubAccount_BE.receipt.adapter.in.web.api;

import com.ClubAccount_BE.core.meta.LoginUser;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.request.ReceiptRequest;
import com.ClubAccount_BE.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Upload Receipt", description = "영수증 등록 API")
public interface CreateReceiptApi {

    @Operation(summary = "영수증 등록", description = "파싱된 영수증 정보를 등록한다.")
    Long createReceipt(
            @LoginUser User user,
            @RequestPart(value = "image", required = false) MultipartFile image,
            @Valid @RequestPart(value = "request") ReceiptRequest receiptRequest
    );
}
