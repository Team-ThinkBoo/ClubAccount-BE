package com.ClubAccount_BE.receipt.application.port.in;

import com.ClubAccount_BE.receipt.adapter.in.web.dto.request.ReceiptRequest;
import com.ClubAccount_BE.user.domain.User;

public interface UpdateReceiptUseCase {

    Long updateReceipt(User user, Long receiptId, ReceiptRequest receiptRequest);
}
