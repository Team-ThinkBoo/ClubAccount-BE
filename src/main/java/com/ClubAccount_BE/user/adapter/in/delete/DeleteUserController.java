package com.ClubAccount_BE.user.adapter.in.delete;

import com.ClubAccount_BE.core.meta.LoginUser;
import com.ClubAccount_BE.user.application.port.in.delete.DeleteUserUseCase;
import com.ClubAccount_BE.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class DeleteUserController {
    private final DeleteUserUseCase deleteUserUseCase;

    @DeleteMapping("/delete")
    public void deleteMyAccount(@LoginUser User user) {
        deleteUserUseCase.deleteUser(user);
    }
}
