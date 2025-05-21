package com.ClubAccount_BE.receipt.application.port.out;

import org.springframework.web.multipart.MultipartFile;

public interface UploadReceiptImagePort {

    String uploadReceipt(MultipartFile image);
}
