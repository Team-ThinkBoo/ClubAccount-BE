package com.ClubAccount_BE.receipt.application.port.out;

import com.ClubAccount_BE.receipt.domain.Receipt;

public interface CreateReceiptPort {

    Long createReceipt(Receipt receipt);
}
