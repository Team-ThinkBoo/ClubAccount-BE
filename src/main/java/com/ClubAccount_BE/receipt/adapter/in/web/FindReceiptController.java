package com.ClubAccount_BE.receipt.adapter.in.web;

import com.ClubAccount_BE.core.response.PagingResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.FindReceiptResponseDto;
import com.ClubAccount_BE.receipt.application.port.in.FindReceiptUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/receipts")
public class FindReceiptController implements FindReceiptApi {

    private final FindReceiptUseCase findReceiptUseCase;

    @GetMapping("/all")
    public PagingResponse<FindReceiptResponseDto> getReceipts(
            @PageableDefault(page = 1, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return findReceiptUseCase.getReceipts(pageable);
    }
}
