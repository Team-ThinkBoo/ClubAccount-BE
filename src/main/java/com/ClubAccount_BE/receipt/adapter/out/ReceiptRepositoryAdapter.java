package com.ClubAccount_BE.receipt.adapter.out;

import com.ClubAccount_BE.receipt.adapter.out.persistence.entity.ReceiptEntity;
import com.ClubAccount_BE.receipt.adapter.out.persistence.repository.ReceiptRepository;
import com.ClubAccount_BE.receipt.application.port.out.CreateReceiptPort;
import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.receipt.mapper.ReceiptMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReceiptRepositoryAdapter implements CreateReceiptPort {

    private final ReceiptMapper receiptMapper;

    private final ReceiptRepository receiptRepository;

    @Override
    public Long createReceipt(Receipt receipt) {
        ReceiptEntity receiptEntity = receiptRepository.save(receiptMapper.mapToJpaEntity(receipt));
        return receiptEntity.getId();
    }

}
