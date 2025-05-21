package com.ClubAccount_BE.receipt.application.service;

import com.ClubAccount_BE.receipt.application.port.in.DeleteReceiptUseCase;
import com.ClubAccount_BE.receipt.application.port.out.DeleteReceiptPort;
import com.ClubAccount_BE.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteReceiptService implements DeleteReceiptUseCase {

    private final DeleteReceiptPort deleteReceiptPort;

    @Override
    public void deleteReceiptList(User user, List<Long> receiptIds) {
        deleteReceiptPort.deleteReceiptList(user, receiptIds);
    }
}
