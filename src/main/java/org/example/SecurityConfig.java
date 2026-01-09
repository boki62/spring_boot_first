package org.example.config.auth; // 패키지 경로를 본인 프로젝트에 맞게 확인하세요

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor // 1. 롬복으로 생성자 주입을 자동으로 생성하거나
public class SecurityConfig {
    // 2. 변수 선언 (final 필수)
    private final CustomOAuth2UserService customOAuth2UserService;

    /* 만약 @RequiredArgsConstructor가 작동하지 않는다면 아래처럼 생성자를 직접 만드세요.
    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }
    */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
                        .disable() // 테스트를 위해 일시적으로 전체 disable 권장
                )
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                .authorizeHttpRequests(auth -> auth
                        // 1. 누구나 접근 가능한 경로 설정
                        .requestMatchers(
                                new AntPathRequestMatcher("/"),
                                new AntPathRequestMatcher("/css/**"),
                                new AntPathRequestMatcher("/images/**"),
                                new AntPathRequestMatcher("/js/**"),
                                new AntPathRequestMatcher("/h2-console/**"),
                                new AntPathRequestMatcher("/login/**"),      // 로그인 관련 경로 허용
                                new AntPathRequestMatcher("/oauth2/**")     // OAuth2 관련 경로 허용
                        ).permitAll()
                        // 2. 글 등록 등 API는 USER 권한 필요
                        .requestMatchers(new AntPathRequestMatcher("/api/v1/**")).hasRole("USER")
                        .anyRequest().authenticated()
                )
                // 3. 로그아웃 설정
                .logout(logout -> logout.logoutSuccessUrl("/"))
                // 4. OAuth2 로그인 설정 (이 부분이 누락되었는지 확인하세요)
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService) // 사용자의 UserService 클래스 연결
                        )
                );

        return http.build();
    }
    }