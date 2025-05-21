package com.ClubAccount_BE.receipt.adapter.in.web.api;

import com.ClubAccount_BE.core.meta.LoginUser;
import com.ClubAccount_BE.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Delete Receipt", description = "영수증 삭제 API")
public interface DeleteReceiptApi {

    @Operation(summary = "영수증 삭제", description = "등록된 영수증 정보를 삭제한다.")
    ResponseEntity<Void> deleteReceiptList(
            @LoginUser User user,
            @NotEmpty @RequestParam List<Long> receiptIds
    );
}
