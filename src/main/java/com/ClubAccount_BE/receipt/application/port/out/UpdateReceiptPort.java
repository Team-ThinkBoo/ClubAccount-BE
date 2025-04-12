package com.ClubAccount_BE.receipt.application.port.out;

import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.receipt.domain.ReceiptItem;
import java.util.List;

public interface UpdateReceiptPort {

    Long updateReceipt(Long receiptId, Receipt receipt, List<ReceiptItem> receiptItem);
}
