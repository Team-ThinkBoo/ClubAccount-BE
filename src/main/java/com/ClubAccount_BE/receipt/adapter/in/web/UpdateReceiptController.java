package com.ClubAccount_BE.receipt.adapter.in.web;

import com.ClubAccount_BE.core.meta.LoginUser;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.request.ReceiptRequest;
import com.ClubAccount_BE.receipt.application.port.in.UpdateReceiptUseCase;
import com.ClubAccount_BE.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/receipts")
public class UpdateReceiptController implements UpdateReceiptApi {

    private final UpdateReceiptUseCase updateReceiptUseCase;

    @PutMapping("/{receiptId}")
    public Long updateReceipt(
            @LoginUser User user,
            @PathVariable("receiptId") Long receiptId,
            @RequestBody ReceiptRequest receiptRequest
    ) {
        return updateReceiptUseCase.updateReceipt(user, receiptId, receiptRequest);
    }
}
