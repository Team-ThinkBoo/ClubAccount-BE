package com.ClubAccount_BE.receipt.adapter.in.web;

import com.ClubAccount_BE.core.meta.LoginUser;
import com.ClubAccount_BE.receipt.adapter.in.web.api.DeleteReceiptApi;
import com.ClubAccount_BE.receipt.application.port.in.DeleteReceiptUseCase;
import com.ClubAccount_BE.user.domain.User;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/receipts")
public class DeleteReceiptController implements DeleteReceiptApi {

    private final DeleteReceiptUseCase deleteReceiptUseCase;

    @DeleteMapping("/receipts")
    public ResponseEntity<Void> deleteReceiptList(
            @LoginUser User user,
            @NotEmpty @RequestParam List<Long> receiptIds
    ) {
        deleteReceiptUseCase.deleteReceiptList(user, receiptIds);
        return ResponseEntity.noContent().build();
    }
}
