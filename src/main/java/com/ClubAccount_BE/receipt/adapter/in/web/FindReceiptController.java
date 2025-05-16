package com.ClubAccount_BE.receipt.adapter.in.web;

import com.ClubAccount_BE.core.response.PagingResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.api.FindReceiptApi;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptCategoryExpenseResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptItemResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptMonthlyExpenseResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptResponse;
import com.ClubAccount_BE.receipt.application.port.in.FindReceiptUseCase;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FindReceiptController implements FindReceiptApi {

    private final FindReceiptUseCase findReceiptUseCase;

    @GetMapping("/{link}/receipts")
    public PagingResponse<ReceiptResponse> getReceiptList(
            @PathVariable(value = "link") UUID link,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @PageableDefault(page = 1, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return findReceiptUseCase.getReceiptList(link, startDate, endDate, pageable);
    }

    @GetMapping("/{link}/receipts/{receiptId}")
    public List<ReceiptItemResponse> getReceiptItem(
            @PathVariable(value = "link") UUID link,
            @PathVariable("receiptId") Long receiptId
    ) {
        return findReceiptUseCase.getReceiptItem(link, receiptId);
    }

    @GetMapping("/{link}/receipts/category")
    public ReceiptCategoryExpenseResponse getReceiptCategoryExpense(
            @PathVariable(value = "link") UUID link
    ) {
        return findReceiptUseCase.getReceiptCategoryExpense(link);
    }

    @GetMapping("/{link}/receipts/expense")
    public List<ReceiptMonthlyExpenseResponse> getReceiptMonthlyExpenseList(
            @PathVariable(value = "link") UUID link,
            @Positive(message = "유효하지 않은 연도입니다.") @RequestParam int year
    ) {
        return findReceiptUseCase.getReceiptMonthlyExpenseList(link, year);
    }
}
