package com.ClubAccount_BE.receipt.adapter.in.web.api;

import com.ClubAccount_BE.core.response.PagingResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptCategoryExpenseResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptItemResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptMonthlyExpenseResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Find Receipt", description = "영수증 조회 API")
public interface FindReceiptApi {

    @Operation(
            summary = "영수증 목록 조회",
            description = "시작일과 종료일을 기준으로 파싱된 영수증을 조회한다. 시작일과 종료일에 정보가 없을 경우 모든 영수증을 조회한다.")
    PagingResponse<ReceiptResponse> getReceiptList(
            @PathVariable(value = "link") UUID link,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @PageableDefault(page = 1, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable
    );

    @Operation(summary = "영수증 상세 목록 조회", description = "파싱된 영수증 상세 아이템을 조회한다.")
    List<ReceiptItemResponse> getReceiptItem(
            @PathVariable(value = "link") UUID link,
            @PathVariable("receiptId") Long receiptId
    );

    @Operation(summary = "영수증 카테고리별 지출 조회", description = "등록된 영수증의 카테고리별 지출을 조회한다.")
    ReceiptCategoryExpenseResponse getReceiptCategoryExpense(
            @PathVariable(value = "link") UUID link
    );

    @Operation(summary = "영수증 월별 지출 목록 조회", description = "등록된 영수증의 월별 지출을 조회한다.")
    List<ReceiptMonthlyExpenseResponse> getReceiptMonthlyExpenseList(
            @PathVariable(value = "link") UUID link,
            @Positive(message = "유효하지 않은 연도입니다.") @RequestParam int year
    );
}
