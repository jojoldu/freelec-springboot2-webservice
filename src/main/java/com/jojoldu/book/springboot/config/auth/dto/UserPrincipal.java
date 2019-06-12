package com.jojoldu.book.springboot.config.auth.dto;

import com.jojoldu.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class UserPrincipal implements UserDetails {
    private String name;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    @Builder
    public UserPrincipal(String name, String email, Collection<? extends GrantedAuthority> authorities) {
        this.name = name;
        this.email = email;
        this.authorities = authorities;
    }

    public static UserPrincipal of(User user) {
        return UserPrincipal.builder()
                .name(user.getName())
                .email(user.getEmail())
                .authorities(Collections.singletonList(user.getRole().getAuthority()))
                .build();
    }


    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return email;
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
}
