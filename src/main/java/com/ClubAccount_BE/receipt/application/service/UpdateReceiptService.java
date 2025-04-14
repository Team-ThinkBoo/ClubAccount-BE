package com.ClubAccount_BE.receipt.application.service;

import com.ClubAccount_BE.receipt.adapter.in.web.dto.request.ReceiptRequest;
import com.ClubAccount_BE.receipt.application.port.in.UpdateReceiptUseCase;
import com.ClubAccount_BE.receipt.application.port.out.UpdateReceiptPort;
import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.receipt.domain.ReceiptItem;
import com.ClubAccount_BE.receipt.domain.service.ReceiptItemEditor;
import com.ClubAccount_BE.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateReceiptService implements UpdateReceiptUseCase {

    private final UpdateReceiptPort updateReceiptPort;

    private final ReceiptItemEditor receiptItemEditor;

    @Override
    public Long updateReceipt(
            User user,
            Long receiptId,
            ReceiptRequest receiptRequest
    ) {
        Receipt receipt = Receipt.update(
                receiptId,
                user,
                receiptRequest.category(),
                receiptRequest.businessName(),
                receiptRequest.date(),
                receiptRequest.amount(),
                receiptRequest.etc()
        );

        List<ReceiptItem> receiptItems = receiptItemEditor.toReceiptItems(receiptRequest, receipt);
        return updateReceiptPort.updateReceipt(receiptId, receipt, receiptItems);
    }
}
