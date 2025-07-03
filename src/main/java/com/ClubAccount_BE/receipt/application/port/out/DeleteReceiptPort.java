package com.ClubAccount_BE.receipt.application.port.out;

import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.user.domain.User;
import java.util.List;

public interface DeleteReceiptPort {

    List<Receipt> deleteReceipts(User user, List<Long> receiptIds);
}
