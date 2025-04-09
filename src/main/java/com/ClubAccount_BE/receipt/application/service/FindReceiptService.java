package com.ClubAccount_BE.receipt.application.service;

import com.ClubAccount_BE.core.response.PagingResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.FindReceiptResponseDto;
import com.ClubAccount_BE.receipt.application.port.in.FindReceiptUseCase;
import com.ClubAccount_BE.receipt.application.port.out.FindReceiptPort;
import com.ClubAccount_BE.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FindReceiptService implements FindReceiptUseCase {

    private final FindReceiptPort findReceiptPort;

    @Override
    public PagingResponse<FindReceiptResponseDto> getReceipts(User user, Pageable pageable) {
        Page<FindReceiptResponseDto> page = findReceiptPort
                .getReceipts(user, pageable)
                .map(FindReceiptResponseDto::of);

        return PagingResponse.of(page);
    }
}
