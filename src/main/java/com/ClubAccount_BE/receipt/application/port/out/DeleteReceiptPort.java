package com.ClubAccount_BE.receipt.application.port.out;

import com.ClubAccount_BE.user.domain.User;
import java.util.List;

public interface DeleteReceiptPort {

    void deleteReceiptList(User user, List<Long> receiptIds);

}
