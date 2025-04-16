package com.ClubAccount_BE.user.adapter.in.signup.validator;

import com.ClubAccount_BE.core.meta.PasswordMatch;
import com.ClubAccount_BE.user.adapter.in.signup.dto.request.SignUpRequest;
import com.ClubAccount_BE.user.adapter.in.update.dto.request.PasswordResetRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return switch (value) {
            case null -> true;
            case SignUpRequest request -> request.getPassword() != null
                    && request.getPasswordCheck() != null
                    && request.getPassword().equals(request.getPasswordCheck());
            case PasswordResetRequest request -> request.newPassword() != null
                    && request.confirmPassword() != null
                    && request.newPassword().equals(request.confirmPassword());
            default ->
                    true;
        };
    }
}