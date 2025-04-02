package com.ClubAccount_BE.user.application.port.out;

public interface CheckUserPort {
    boolean checkDuplicateAuthId(String authId);
}
