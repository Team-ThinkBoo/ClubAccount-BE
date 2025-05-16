package com.ClubAccount_BE.receipt.application.port.in;

import com.ClubAccount_BE.core.response.PagingResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptCategoryExpenseResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptExpenseResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptItemResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface FindReceiptUseCase {

    ReceiptCategoryExpenseResponse getReceiptCategoryExpense(UUID link);

    PagingResponse<ReceiptResponse> getReceiptList(
            UUID link,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    );

    List<ReceiptItemResponse> getReceiptItem(UUID link, Long receiptId);

    List<ReceiptExpenseResponse> getReceiptExpenseList(UUID link, int year);
}
