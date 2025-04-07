package com.ClubAccount_BE.receipt.application.port.in;

import com.ClubAccount_BE.core.response.PagingResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.FindReceiptResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public interface FindReceiptUseCase {

    PagingResponse<FindReceiptResponseDto> getReceipts(Pageable pageable);
}
