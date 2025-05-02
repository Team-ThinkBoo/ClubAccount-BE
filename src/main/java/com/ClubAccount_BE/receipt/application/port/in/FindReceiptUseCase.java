package com.ClubAccount_BE.receipt.application.port.in;

import com.ClubAccount_BE.core.response.PagingResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptCategoryResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptDetailResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptResponse;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface FindReceiptUseCase {

    ReceiptCategoryResponse getReceiptCategoryRatio(UUID link);

    PagingResponse<ReceiptResponse> getReceiptList(UUID link, Pageable pageable);

    ReceiptDetailResponse getReceipt(UUID link, Long receiptId);
}
