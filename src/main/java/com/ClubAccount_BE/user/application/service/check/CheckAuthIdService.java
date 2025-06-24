package com.ClubAccount_BE.user.application.service.check;

import com.ClubAccount_BE.user.adapter.in.signup.dto.response.AuthIdDuplicationResponse;
import com.ClubAccount_BE.user.application.port.in.check.CheckAuthIdUseCase;
import com.ClubAccount_BE.user.application.port.out.CheckUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
class CheckAuthIdService implements CheckAuthIdUseCase {

    private final CheckUserPort checkUserPort;

    @Override
    public AuthIdDuplicationResponse checkAuthIdDuplication(String authId) {
        boolean authIdDuplication = !checkUserPort.checkDuplicateAuthId(authId);
        return new AuthIdDuplicationResponse(authId, authIdDuplication);
    }
}
