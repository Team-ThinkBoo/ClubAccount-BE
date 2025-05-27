package com.ClubAccount_BE.user.application.service.delete;

import com.ClubAccount_BE.receipt.application.port.out.DeleteReceiptImagePort;
import com.ClubAccount_BE.receipt.application.port.out.FindReceiptPort;
import com.ClubAccount_BE.receipt.domain.Receipt;
import com.ClubAccount_BE.user.application.port.in.delete.DeleteUserUseCase;
import com.ClubAccount_BE.user.application.port.out.UserPort;

import com.ClubAccount_BE.user.application.port.out.delete.DeleteProfileImagePort;
import com.ClubAccount_BE.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteUserService implements DeleteUserUseCase {

    private final UserPort userPort;
    private final FindReceiptPort findReceiptPort;
    private final DeleteReceiptImagePort deleteReceiptImagePort;
    private final DeleteProfileImagePort deleteProfileImagePort;

    @Override
    public void deleteUser(User user) {

        deleteProfileImagePort.deleteImages(user.getProfileUrl());

        List<Receipt> receipts = findReceiptPort.getAllReceipts(user);
        List<String> receiptImageKeys = receipts.stream()
                .map(Receipt::getReceiptImageUrl)
                .filter(Objects::nonNull)
                .toList();
        deleteReceiptImagePort.deleteImages(receiptImageKeys);

        // 회원 삭제
        userPort.delete(user);
    }
}