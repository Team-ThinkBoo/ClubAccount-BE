package com.ClubAccount_BE.receipt.adapter.out;

import com.ClubAccount_BE.receipt.adapter.out.persistence.entity.ReceiptEntity;
import com.ClubAccount_BE.receipt.adapter.out.persistence.repository.ReceiptRepository;
import com.ClubAccount_BE.receipt.application.port.out.CreateReceiptPort;
import com.ClubAccount_BE.receipt.application.port.out.FindReceiptPort;
import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.receipt.domain.ReceiptItem;
import com.ClubAccount_BE.receipt.mapper.ReceiptMapper;
import com.ClubAccount_BE.user.domain.User;
import com.ClubAccount_BE.user.mapper.UserMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReceiptRepositoryAdapter implements CreateReceiptPort, FindReceiptPort {

    private final ReceiptRepository receiptRepository;

    @Override
    public Long createReceipt(Receipt receipt, List<ReceiptItem> receiptItems) {

        ReceiptEntity receiptEntity = ReceiptMapper.toEntity(receipt);
        ReceiptMapper.toEntity(receiptItems, receiptEntity);

        return receiptRepository
                .save(receiptEntity)
                .getId();
    }

    @Override
    public Page<Receipt> getReceipts(User user, Pageable pageable) {
        return receiptRepository
                .findAllByUserId(user.getId(), pageable)
                .map(ReceiptMapper::toDomain);
    }

    @Override
    public Receipt getReceipt(User user, Long receiptId) {
        return receiptRepository
                .findByUserAndId(UserMapper.toEntity(user), receiptId)
                .map(ReceiptMapper::toDomain)
                .orElseThrow(() -> new IllegalArgumentException("Receipt not found"));
    }
}
