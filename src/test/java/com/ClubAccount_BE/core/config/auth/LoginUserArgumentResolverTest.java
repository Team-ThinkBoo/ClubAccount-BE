package com.ClubAccount_BE.core.config.auth;

import com.ClubAccount_BE.core.exception.UnAuthorizedException;
import com.ClubAccount_BE.user.application.port.in.FindUserUseCase;
import com.ClubAccount_BE.user.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginUserArgumentResolverTest {

    @Mock
    private FindUserUseCase findUserUseCase;

    @InjectMocks
    private LoginUserArgumentResolver resolver;

    @Mock
    private MethodParameter methodParameter;

    @Mock
    private ModelAndViewContainer mavContainer;

    @Mock
    private NativeWebRequest webRequest;

    @Mock
    private WebDataBinderFactory binderFactory;

    @Test
    void 인증_정보가_없으면_예외발생() {
        // given
        SecurityContextHolder.clearContext(); // 인증 정보 없음

        // when & then
        assertThrows(UnAuthorizedException.class, () -> {
            resolver.resolveArgument(methodParameter, mavContainer, webRequest, binderFactory);
        });
    }

    @Test
    void 인증_정보가_있으면_User_반환(){
        // given
        Long authId = 1L;
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getName()).thenReturn(authId.toString());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User mockUser = User.builder().id(authId).build();
        when(findUserUseCase.getUserById(authId)).thenReturn(mockUser);

        // when
        Object result = resolver.resolveArgument(methodParameter, mavContainer, webRequest, binderFactory);

        // then
        assertThat(result).isEqualTo(mockUser);
    }
}