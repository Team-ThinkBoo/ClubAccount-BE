package com.ClubAccount_BE.receipt.application.service;

import static com.ClubAccount_BE.core.exception.ErrorCode.RECEIPT_INVALID_START_DATE;

import com.ClubAccount_BE.core.exception.ApiException;
import com.ClubAccount_BE.core.response.PagingResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptCategoryExpenseResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptItemResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptMonthlyExpenseResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptResponse;
import com.ClubAccount_BE.receipt.application.port.in.FindReceiptUseCase;
import com.ClubAccount_BE.receipt.application.port.out.FindReceiptPort;
import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.receipt.domain.CategoryExpenseResult;
import com.ClubAccount_BE.receipt.domain.MonthlyExpenseResult;
import com.ClubAccount_BE.user.application.port.out.FindUserPort;
import com.ClubAccount_BE.user.domain.User;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
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
    private final FindUserPort findUserPort;

    @Override
    public PagingResponse<ReceiptResponse> getReceiptsByDate(
            UUID link,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    ) {
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            throw new ApiException(RECEIPT_INVALID_START_DATE);
        }

        User user = findUserPort.getUserByLink(link);
        Page<ReceiptResponse> page = findReceiptPort
                .getReceiptsByDate(user, startDate, endDate, pageable)
                .map(ReceiptResponse::of);

        return PagingResponse.of(page);
    }

    @Override
    public List<ReceiptItemResponse> getReceiptItem(UUID link, Long receiptId) {
        User user = findUserPort.getUserByLink(link);
        Receipt receipt = findReceiptPort.getReceipt(user, receiptId);
        return receipt.getReceiptItems()
                .stream()
                .map(ReceiptItemResponse::of)
                .toList();
    }

    @Override
    public List<ReceiptMonthlyExpenseResponse> getReceiptExpenseByMonth(UUID link, int year) {
        User user = findUserPort.getUserByLink(link);
        List<MonthlyExpenseResult> result = findReceiptPort.getReceiptExpenseByMonth(user, year);
        return result.stream()
                .map(ReceiptMonthlyExpenseResponse::of)
                .toList();
    }


    @Override
    public List<ReceiptCategoryExpenseResponse> getReceiptExpenseByCategory(UUID link) {
        User user = findUserPort.getUserByLink(link);
        List<CategoryExpenseResult> result = findReceiptPort.getReceiptExpenseByCategory(user);
        return result.stream()
                .map(ReceiptCategoryExpenseResponse::of)
                .toList();
    }
}
