package com.ClubAccount_BE.receipt.application.service;

import com.ClubAccount_BE.receipt.application.port.in.DeleteReceiptUseCase;
import com.ClubAccount_BE.receipt.application.port.out.DeleteReceiptImagePort;
import com.ClubAccount_BE.receipt.application.port.out.DeleteReceiptPort;
import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.receipt.domain.service.ReceiptEditor;
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
    private final ReceiptEditor receiptEditor;

    @Override
    public void deleteReceiptList(User user, List<Long> receiptIds) {
        List<Receipt> receiptList = deleteReceiptPort.deleteReceiptList(user, receiptIds);
        List<String> receiptImage = receiptEditor.deleteReceiptImage(receiptList);
        deleteReceiptImagePort.deleteImages(receiptImage);
    }
}
