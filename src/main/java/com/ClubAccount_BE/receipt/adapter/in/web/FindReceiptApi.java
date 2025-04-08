package com.ClubAccount_BE.receipt.adapter.in.web;

import com.ClubAccount_BE.core.response.PagingResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.FindReceiptResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

@Tag(name = "Find Receipt", description = "영수증 조회 API")
public interface FindReceiptApi {

    @Operation(summary = "영수증 목록 조회", description = "파싱된 영수증 정보를 조회한다.")
    PagingResponse<FindReceiptResponseDto> getReceipts(
            @PageableDefault(page = 1, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable
    );
}
