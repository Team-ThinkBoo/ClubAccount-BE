package com.ClubAccount_BE.receipt.application.service;

import static com.ClubAccount_BE.core.constant.CommonConstant.DEFAULT_IMAGE;

import com.ClubAccount_BE.receipt.application.port.in.DeleteReceiptUseCase;
import com.ClubAccount_BE.receipt.application.port.out.DeleteReceiptImagePort;
import com.ClubAccount_BE.receipt.application.port.out.DeleteReceiptPort;
import com.ClubAccount_BE.receipt.domain.Receipt;
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
    private final DeleteReceiptImagePort deleteReceiptImagePort;

    @Override
    public void deleteReceiptList(User user, List<Long> receiptIds) {
        List<Receipt> receiptList = deleteReceiptPort.deleteReceipts(user, receiptIds);
        List<String> receiptImage = receiptList.stream()
                .filter(receipt -> receipt.isDefaultImage(DEFAULT_IMAGE))
                .map(Receipt::getReceiptImageUrl)
                .toList();
        deleteReceiptImagePort.deleteImages(receiptImage);
    }
}
