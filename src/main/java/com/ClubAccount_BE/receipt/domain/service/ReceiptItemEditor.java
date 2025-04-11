package com.ClubAccount_BE.receipt.domain.service;

import com.ClubAccount_BE.receipt.adapter.in.web.dto.request.ReceiptRequest;
import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.receipt.domain.ReceiptItem;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReceiptItemEditor {

    public List<ReceiptItem> toReceiptItems(
            ReceiptRequest receiptRequest,
            Receipt receipt
    ) {
        return receiptRequest.receiptItems().stream()
                .map(receiptItem -> ReceiptItem.of(
                        receipt,
                        receiptItem.name(),
                        receiptItem.price(),
                        receiptItem.totalPrice(),
                        receiptItem.quantity()
                ))
                .toList();
    }

}
