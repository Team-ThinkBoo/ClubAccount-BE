package com.ClubAccount_BE.receipt.application.service;

import static com.ClubAccount_BE.core.constant.CommonConstant.DEFAULT_IMAGE;

import com.ClubAccount_BE.receipt.adapter.in.web.dto.request.ReceiptRequest;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.response.ReceiptCreateResponse;
import com.ClubAccount_BE.receipt.application.port.in.CreateReceiptUseCase;
import com.ClubAccount_BE.receipt.application.port.out.CreateReceiptPort;
import com.ClubAccount_BE.receipt.application.port.out.UploadReceiptPort;
import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.receipt.domain.ReceiptItem;
import com.ClubAccount_BE.receipt.domain.service.ReceiptItemEditor;
import com.ClubAccount_BE.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CreateReceiptService implements CreateReceiptUseCase {

    private final CreateReceiptPort createReceiptPort;

    private final UploadReceiptPort uploadReceiptPort;

    private final ReceiptItemEditor receiptItemEditor;

    @Override
    public ReceiptCreateResponse createReceipt(
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
                image == null ? DEFAULT_IMAGE : uploadReceiptPort.uploadReceipt(image)
        );

        List<ReceiptItem> receiptItems = receiptItemEditor.toReceiptItems(receiptRequest, receipt);
        Long receiptId = createReceiptPort.createReceipt(receipt, receiptItems);
        return ReceiptCreateResponse.of(receiptId, receipt);
    }
}
