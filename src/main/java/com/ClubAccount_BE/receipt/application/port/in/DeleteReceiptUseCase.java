package com.ClubAccount_BE.receipt.application.port.in;

import com.ClubAccount_BE.user.domain.User;
import java.util.List;

public interface DeleteReceiptUseCase {

    void deleteReceiptList(User user, List<Long> receiptIds);
}
