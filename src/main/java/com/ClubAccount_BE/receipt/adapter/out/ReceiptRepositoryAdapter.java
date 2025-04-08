package com.ClubAccount_BE.receipt.adapter.out;

import com.ClubAccount_BE.receipt.adapter.out.persistence.repository.ReceiptRepository;
import com.ClubAccount_BE.receipt.application.port.out.CreateReceiptPort;
import com.ClubAccount_BE.receipt.application.port.out.FindReceiptPort;
import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.receipt.mapper.ReceiptMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReceiptRepositoryAdapter implements CreateReceiptPort, FindReceiptPort {

    private final ReceiptRepository receiptRepository;

    @Override
    public Long createReceipt(Receipt receipt) {
        return receiptRepository
                .save(ReceiptMapper.toEntity(receipt))
                .getId();
    }

    @Override
    public Page<Receipt> getReceipts(Pageable pageable) {
        return receiptRepository
                .findAll(pageable)
                .map(ReceiptMapper::toDomain);
    }
}
