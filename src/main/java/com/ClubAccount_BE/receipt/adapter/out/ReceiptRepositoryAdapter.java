package com.ClubAccount_BE.receipt.adapter.out;

import static com.ClubAccount_BE.core.exception.ErrorCode.RECEIPT_NOT_FOUND;

import com.ClubAccount_BE.core.exception.ApiException;
import com.ClubAccount_BE.receipt.adapter.out.persistence.entity.ReceiptEntity;
import com.ClubAccount_BE.receipt.adapter.out.persistence.repository.ReceiptRepository;
import com.ClubAccount_BE.receipt.application.port.out.CreateReceiptPort;
import com.ClubAccount_BE.receipt.application.port.out.FindReceiptPort;
import com.ClubAccount_BE.receipt.application.port.out.UpdateReceiptPort;
import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.receipt.domain.ReceiptItem;
import com.ClubAccount_BE.receipt.mapper.ReceiptMapper;
import com.ClubAccount_BE.user.domain.User;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReceiptRepositoryAdapter
        implements CreateReceiptPort, FindReceiptPort, UpdateReceiptPort {

    private final ReceiptRepository receiptRepository;

    @Override
    public Long createReceipt(Receipt receipt, List<ReceiptItem> receiptItems) {
        ReceiptEntity receiptEntity = ReceiptMapper.toEntity(receipt);
        receiptEntity.replaceReceiptItem(receiptItems);
        return receiptRepository
                .save(receiptEntity)
                .getId();
    }

    @Override
    public Page<Receipt> getReceiptList(
            User user,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    ) {
        return receiptRepository
                .findAllByDate(user.getId(), startDate, endDate, pageable)
                .map(ReceiptMapper::toDomain);
    }

    @Override
    public Receipt getReceipt(User user, Long receiptId) {
        return receiptRepository
                .findById(receiptId)
                .map(ReceiptMapper::toDomain)
                .orElseThrow(() -> new ApiException(RECEIPT_NOT_FOUND));
    }

    @Override
    public List<Receipt> getReceiptCategoryList(User user) {
        return receiptRepository
                .findAllByUserId(user.getId())
                .stream()
                .map(ReceiptMapper::toDomain)
                .toList();
    }

    @Override
    public Long updateReceipt(
            Long receiptId,
            Receipt receipt,
            List<ReceiptItem> receiptItems
    ) {
        ReceiptEntity receiptEntity = receiptRepository.findReceiptById(receiptId)
                .orElseThrow(() -> new ApiException(RECEIPT_NOT_FOUND));

        receiptEntity.updateReceipt(receipt);
        receiptEntity.replaceReceiptItem(receiptItems);
        return receiptEntity.getId();
    }
}
