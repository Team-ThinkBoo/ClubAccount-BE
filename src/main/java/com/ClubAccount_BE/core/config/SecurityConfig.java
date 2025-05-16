package com.ClubAccount_BE.core.config;

import com.ClubAccount_BE.auth.security.JwtAccessDeniedHandler;
import com.ClubAccount_BE.auth.security.JwtAuthenticationEntryPoint;
import com.ClubAccount_BE.auth.security.JwtAuthenticationProvider;
import com.ClubAccount_BE.auth.security.TokenAuthenticationFilter;
import com.ClubAccount_BE.auth.security.TokenProvider;
import com.ClubAccount_BE.user.application.port.in.FindUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    public static final String API_V1_PREFIX = "/api/v1";

    private final TokenProvider tokenProvider;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                API_V1_PREFIX + "/users/sign-up/**",
                                API_V1_PREFIX + "/auth/sign-in",
                                API_V1_PREFIX + "/auth/token",
                                API_V1_PREFIX + "/users/{student-number}/validate",
                                API_V1_PREFIX + "/users/password",
                                API_V1_PREFIX + "/health",
                                API_V1_PREFIX + "/users/sign-up/check-duplicate-auth-id",
                                API_V1_PREFIX + "/email/send",
                                API_V1_PREFIX + "/email/verify",
                                API_V1_PREFIX + "/auth/reset-password",
                                API_V1_PREFIX + "/{link}/receipts",
                                API_V1_PREFIX + "/{link}/receipts/{receiptId}",
                                API_V1_PREFIX + "/{link}/receipts/category",
                                API_V1_PREFIX + "/{link}/receipts/expense",
                                "/api-docs",
                                "/swagger-custom-ui.html",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/api-docs/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .headers(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .rememberMe(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                )
                .sessionManagement(sess -> sess
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .cors(Customizer.withDefaults())
                .addFilterBefore(tokenAuthenticationFilter(tokenProvider),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            JwtAuthenticationProvider jwtAuthenticationProvider) {
        return new ProviderManager(jwtAuthenticationProvider);
    }

    @Bean
    public JwtAuthenticationProvider jwtAuthenticationProvider(
            PasswordEncoder passwordEncoder,
            FindUserUseCase findUserUseCase
    ) {
        return new JwtAuthenticationProvider(passwordEncoder, findUserUseCase);
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter(TokenProvider tokenProvider) {
        return new TokenAuthenticationFilter(tokenProvider);
    }
}
