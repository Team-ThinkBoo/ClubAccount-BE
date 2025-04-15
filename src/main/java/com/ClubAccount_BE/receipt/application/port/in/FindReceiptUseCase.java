package com.ClubAccount_BE.receipt.application.port.in;

import com.ClubAccount_BE.core.response.PagingResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptDetailResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptResponse;
import com.ClubAccount_BE.user.domain.User;
import org.springframework.data.domain.Pageable;

public interface FindReceiptUseCase {

    PagingResponse<ReceiptResponse> getReceipts(User user, Pageable pageable);

    ReceiptDetailResponse getReceipt(User user, Long receiptId);
}
