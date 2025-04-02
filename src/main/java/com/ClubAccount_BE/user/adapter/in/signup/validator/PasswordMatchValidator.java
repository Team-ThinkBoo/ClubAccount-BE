package com.ClubAccount_BE.user.adapter.in.signup.validator;

import com.ClubAccount_BE.core.meta.PasswordMatch;
import com.ClubAccount_BE.user.adapter.in.signup.dto.request.SignUpRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, SignUpRequest> {

    @Override
    public boolean isValid(SignUpRequest request, ConstraintValidatorContext context) {
        if (request.getPassword() == null || request.getPasswordCheck() == null) {
            return false;
        }
        return request.getPassword().equals(request.getPasswordCheck());
    }
}
