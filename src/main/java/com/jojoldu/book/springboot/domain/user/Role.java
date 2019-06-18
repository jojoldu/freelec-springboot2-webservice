package com.jojoldu.book.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "손님"),
    MEMBER("ROLE_MEMBER", "일반 사용자");

    private final String key;
    private final String title;

    public GrantedAuthority getAuthority() {
        return new SimpleGrantedAuthority(getKey());
    }

}
