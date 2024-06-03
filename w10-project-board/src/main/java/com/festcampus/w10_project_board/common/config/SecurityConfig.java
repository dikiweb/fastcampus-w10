package com.festcampus.w10_project_board.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * packageName   : com.festcampus.w10_project_board.common.config
 * fileName     : SecurityConfig
 * author       : danny
 * date         : 2024-06-03
 * description  :
 * ===========================================================
 * DATE             AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2024-06-03          danny         최초 생성
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 페이지 접근 권한
        http
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        // 사용자 정의 로그인 폼
        http
                .formLogin(formLogin -> formLogin.permitAll());

        return http.build();
    }
}
