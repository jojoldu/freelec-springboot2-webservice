package com.jojoldu.book.springboot.config.auth.dto;

import com.jojoldu.book.springboot.domain.user.Role;
import com.jojoldu.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class GoogleUser {
    private String name;
    private String email;
    private String picture;

    @Builder
    public GoogleUser(String name, String email, String picture) {
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static GoogleUser of(Map<String, Object> attributes) {
        return GoogleUser.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
}
