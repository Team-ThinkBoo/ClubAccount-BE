package com.ClubAccount_BE.receipt.application.port.out;

import com.ClubAccount_BE.receipt.domain.Receipt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindReceiptPort {

    Page<Receipt> getReceipts(Pageable pageable);
}
