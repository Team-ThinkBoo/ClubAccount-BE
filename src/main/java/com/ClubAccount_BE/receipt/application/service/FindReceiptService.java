package com.ClubAccount_BE.receipt.application.service;

import static com.ClubAccount_BE.core.exception.ErrorCode.RECEIPT_INVALID_START_DATE;

import com.ClubAccount_BE.core.exception.ApiException;
import com.ClubAccount_BE.core.response.PagingResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptCategoryResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptDetailResponse;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptResponse;
import com.ClubAccount_BE.receipt.application.port.in.FindReceiptUseCase;
import com.ClubAccount_BE.receipt.application.port.out.FindReceiptPort;
import com.ClubAccount_BE.receipt.domain.DetailCategoryResult;
import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.receipt.domain.service.ReceiptEditor;
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
    private final ReceiptEditor receiptEditor;

    @Override
    public ReceiptDetailResponse getReceipt(UUID link, Long receiptId) {

        User user = findUserPort.getUserByLink(link);
        Receipt receipt = findReceiptPort.getReceipt(user, receiptId);
        return ReceiptDetailResponse.of(receipt);
    }


    @Override
    public PagingResponse<ReceiptResponse> getReceiptList(
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
                .getReceiptList(user, startDate, endDate, pageable)
                .map(ReceiptResponse::of);

        return PagingResponse.of(page);
    }

    @Override
    public ReceiptCategoryResponse getReceiptCategoryRatio(UUID link) {

        User user = findUserPort.getUserByLink(link);
        List<Receipt> receiptList = findReceiptPort.getReceiptCategoryList(user);
        DetailCategoryResult result = receiptEditor.calculateCategoryRatio(receiptList);
        return ReceiptCategoryResponse.of(result);
    }
}
