package com.festcampus.w10_project_board.userAccount.dto.security;

import com.festcampus.w10_project_board.userAccount.dto.UserAccountDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * packageName   : com.festcampus.w10_project_board.userAccount.dto.security
 * fileName     : SitePrincipal
 * author       : danny
 * date         : 2024-06-06
 * description  :
 * ===========================================================
 * DATE             AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2024-06-06          danny         최초 생성
 */
public record SitePrincipal(
    String username,
    String password,
    Collection<? extends GrantedAuthority> authorities,
    String email,
    String nickname,
    String memo
) implements UserDetails {

    public static SitePrincipal of(String username, String password, String email, String nickname, String memo) {

        Set<RoleType> roleTypes = Set.of(RoleType.USER);

        return new SitePrincipal(
                username,
                password,
                roleTypes
                        .stream()
                        .map(RoleType::getName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet()),
                email,
                nickname,
                memo);
    }

    public static SitePrincipal from(UserAccountDto dto) {
        return SitePrincipal.of(
                dto.userId(),
                dto.userPassword(),
                dto.email(),
                dto.nickname(),
                dto.memo()
        );
    }

    public UserAccountDto toDto() {
        return UserAccountDto.of(
                username,
                password,
                email,
                nickname,
                memo);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public enum RoleType {
        USER("ROLE_USER");

        @Getter
        private final String name;

        RoleType(String name) {
            this.name = name;
        }
    }
}
