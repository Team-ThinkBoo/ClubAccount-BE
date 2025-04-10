package com.ClubAccount_BE.receipt.application.port.in;

import com.ClubAccount_BE.core.response.PagingResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.DetailReceiptResponseDto;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.FindReceiptResponseDto;
import com.ClubAccount_BE.user.domain.User;
import org.springframework.data.domain.Pageable;

public interface FindReceiptUseCase {

    PagingResponse<FindReceiptResponseDto> getReceipts(User user, Pageable pageable);

    DetailReceiptResponseDto getReceipt(User user, Long receiptId);
}
