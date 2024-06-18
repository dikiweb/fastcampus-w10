package com.festcampus.w10_project_board.common.config;

import com.festcampus.w10_project_board.userAccount.dto.UserAccountDto;
import com.festcampus.w10_project_board.userAccount.dto.security.KakaoOAuth2Response;
import com.festcampus.w10_project_board.userAccount.dto.security.SitePrincipal;
import com.festcampus.w10_project_board.userAccount.service.UserAccountService;
import jakarta.servlet.DispatcherType;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.security.config.Customizer.withDefaults;

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
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService
    ) throws Exception {

        // 페이지 접근 권한
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers("/assets/**").permitAll()
                        .requestMatchers(
                                HttpMethod.GET,
                                "/",
                                "/articles",
                                "/articles/search-hashtag"
                        ).permitAll()
                        .anyRequest().authenticated()
                );

        // 사용자 정의 로그인 폼
        http.formLogin(withDefaults());

        // 사용자 정의 로그아웃
        http
                .logout(logout -> logout.logoutSuccessUrl("/"));

        // OAuth 인증
        http.oauth2Login(oAuth -> oAuth
                .userInfoEndpoint(userInfo -> userInfo
                        .userService(oAuth2UserService)
                )
        );

        //CSRF  -- ignoringAntMatchers의 새 이름이  ignoringRequestMatchers 이다.
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserAccountService userAccountService) {
        return username -> userAccountService
                .searchUser(username)
                .map(SitePrincipal::from)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다 - username: " + username));
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService(
            UserAccountService userAccountService,
            PasswordEncoder passwordEncoder
            ) {

        final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();

        return userRequest -> {
            OAuth2User oAuth2User = delegate.loadUser(userRequest);

            KakaoOAuth2Response kakaoresponse = KakaoOAuth2Response.from(oAuth2User.getAttributes());

            // 카카오에게 받은 정보로 username 세팅하기
            String registrationId = userRequest.getClientRegistration().getRegistrationId();
            String providerId = String.valueOf(kakaoresponse.id());
            String username = registrationId + "_" + providerId;

            // 인증은 카카오가 하니 비번은 더미로 세팅하기
            String dummyPassword = passwordEncoder.encode("{bcrypt}" + UUID.randomUUID());

            return userAccountService.searchUser(username)
                    .map(SitePrincipal::from)
                    .orElseGet(() ->
                            SitePrincipal.from(
                                    userAccountService.saveUser(
                                            username,
                                            dummyPassword,
                                            kakaoresponse.email(),
                                            kakaoresponse.nickname(),
                                            null
                                    )
                            )
                    );
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
