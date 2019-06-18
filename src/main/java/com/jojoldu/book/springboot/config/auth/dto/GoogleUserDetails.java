package com.jojoldu.book.springboot.config.auth.dto;

import com.jojoldu.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.List;

@Getter
public class GoogleUserDetails implements UserDetails {
    private String email;
    private List<GrantedAuthority> authorities;

    @Builder
    public GoogleUserDetails(String email, List<GrantedAuthority> authorities) {
        this.email = email;
        this.authorities = authorities;
    }

    public static UserDetails of(User entity) {
        return GoogleUserDetails.builder()
                .email(entity.getEmail())
                .authorities(Collections.singletonList(entity.getRole().getAuthority()))
                .build();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return "";
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
