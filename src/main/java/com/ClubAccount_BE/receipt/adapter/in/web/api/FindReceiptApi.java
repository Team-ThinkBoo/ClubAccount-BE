package com.ClubAccount_BE.receipt.adapter.in.web.api;

import com.ClubAccount_BE.core.response.PagingResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptDetailResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Find Receipt", description = "영수증 조회 API")
public interface FindReceiptApi {

    @Operation(summary = "영수증 목록 조회", description = "파싱된 영수증 정보를 조회한다.")
    PagingResponse<ReceiptResponse> getReceiptList(
            @PathVariable(value = "link") UUID link,
            @PageableDefault(page = 1, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable
    );

    @Operation(summary = "영수증 상세 목록 조회", description = "파싱된 영수증 정보를 조회한다.")
    ReceiptDetailResponse getReceipt(
            @PathVariable(value = "link") UUID link,
            @PathVariable("receiptId") Long receiptId
    );
}
