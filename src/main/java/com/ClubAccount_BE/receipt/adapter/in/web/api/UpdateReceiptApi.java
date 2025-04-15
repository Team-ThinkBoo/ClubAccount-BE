package com.ClubAccount_BE.receipt.adapter.in.web.api;

import com.ClubAccount_BE.core.meta.LoginUser;
import com.ClubAccount_BE.receipt.adapter.in.web.dto.request.ReceiptRequest;
import com.ClubAccount_BE.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Update Receipt", description = "영수증 수정 API")
public interface UpdateReceiptApi {

    @Operation(summary = "영수증 수정", description = "파싱된 영수증 정보를 수정한다.")
    Long updateReceipt(
            @LoginUser User user,
            @PathVariable("receiptId") Long receiptId,
            @Valid @RequestBody ReceiptRequest receiptRequest
    );

}
