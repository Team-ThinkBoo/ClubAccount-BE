package com.ClubAccount_BE.receipt.application.port.out;

import java.util.List;

public interface DeleteReceiptImagePort {

    void deleteImages(List<String> receiptImage);
}
