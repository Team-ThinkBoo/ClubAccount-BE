package com.ClubAccount_BE.user.adapter.in.signup.validator;

import com.ClubAccount_BE.core.meta.PasswordMatch;
import com.ClubAccount_BE.user.adapter.in.signup.dto.request.SignUpRequest;
import com.ClubAccount_BE.user.adapter.in.update.dto.request.ResetPasswordRequest;
import com.ClubAccount_BE.user.adapter.in.update.dto.request.LoginUserUpdatePassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MatchPasswordValidator implements ConstraintValidator<PasswordMatch, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return switch (value) {
            case SignUpRequest request -> request.password() != null
                    && request.passwordCheck() != null
                    && request.password().equals(request.passwordCheck());
            case ResetPasswordRequest request -> request.newPassword() != null
                    && request.confirmPassword() != null
                    && request.newPassword().equals(request.confirmPassword());
            case LoginUserUpdatePassword request -> request.newPassword() != null
                    && request.confirmPassword() != null
                    && request.newPassword().equals(request.confirmPassword());
            case null, default -> true;
        };
    }
}