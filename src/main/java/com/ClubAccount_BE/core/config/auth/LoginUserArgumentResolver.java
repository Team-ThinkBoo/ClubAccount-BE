package com.ClubAccount_BE.core.config.auth;

import static com.ClubAccount_BE.core.exception.ErrorCode.AUTH_UNAUTHORIZED;

import com.ClubAccount_BE.core.exception.UnAuthorizedException;
import com.ClubAccount_BE.core.meta.LoginUser;
import com.ClubAccount_BE.user.application.port.in.FindUserUseCase;
import com.ClubAccount_BE.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final FindUserUseCase findUserUseCase;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class) &&
                parameter.getParameterType().equals(User.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        return getAuthenticatedUser();
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnAuthorizedException(AUTH_UNAUTHORIZED);
        }

        Long userId = Long.valueOf(authentication.getName());
        return findUserUseCase.getUserById(userId);
    }
}