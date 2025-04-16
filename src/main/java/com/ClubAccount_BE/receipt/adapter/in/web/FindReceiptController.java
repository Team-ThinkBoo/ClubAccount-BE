package com.ClubAccount_BE.receipt.adapter.in.web;

import com.ClubAccount_BE.core.meta.LoginUser;
import com.ClubAccount_BE.core.response.PagingResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.api.FindReceiptApi;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptDetailResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptResponse;
import com.ClubAccount_BE.receipt.application.port.in.FindReceiptUseCase;
import com.ClubAccount_BE.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/receipts")
public class FindReceiptController implements FindReceiptApi {

    private final FindReceiptUseCase findReceiptUseCase;

    @GetMapping
    public PagingResponse<ReceiptResponse> getReceipts(
            @LoginUser User user,
            @PageableDefault(page = 1, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return findReceiptUseCase.getReceipts(user, pageable);
    }

    @GetMapping("/{receiptId}")
    public ReceiptDetailResponse getReceipt(
            @LoginUser User user,
            @PathVariable("receiptId") Long receiptId
    ) {
        return findReceiptUseCase.getReceipt(user, receiptId);
    }
}
