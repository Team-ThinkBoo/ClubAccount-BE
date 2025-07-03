package com.ClubAccount_BE.receipt.application.service;

import static com.ClubAccount_BE.core.constant.CommonConstant.DEFAULT_IMAGE;

import com.ClubAccount_BE.receipt.adapter.in.web.dto.request.ReceiptRequest;
import com.ClubAccount_BE.receipt.application.port.in.CreateReceiptUseCase;
import com.ClubAccount_BE.receipt.application.port.out.CreateReceiptPort;
import com.ClubAccount_BE.receipt.application.port.out.UploadReceiptImagePort;
import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.receipt.domain.ReceiptItem;
import com.ClubAccount_BE.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateReceiptService implements CreateReceiptUseCase {

    private final CreateReceiptPort createReceiptPort;
    private final UploadReceiptImagePort uploadReceiptImagePort;

    @Override
    public Long createReceipt(
            User user,
            MultipartFile image,
            ReceiptRequest receiptRequest
    ) {
        Receipt receipt = Receipt.create(
                user,
                receiptRequest.category(),
                receiptRequest.businessName(),
                receiptRequest.date(),
                receiptRequest.amount(),
                receiptRequest.etc(),
                image == null ? DEFAULT_IMAGE : uploadReceiptImagePort.uploadReceipt(image)
        );

        List<ReceiptItem> receiptItems = receiptRequest.receiptItems().stream()
                .map(receiptItem -> ReceiptItem.of(
                        receipt,
                        receiptItem.name(),
                        receiptItem.price(),
                        receiptItem.totalPrice(),
                        receiptItem.quantity()
                ))
                .toList();

        receipt.updateAmountMatched(receiptItems);
        return createReceiptPort.createReceipt(receipt, receiptItems);
    }
}
