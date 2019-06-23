package com.jojoldu.book.springboot.config.auth.dto;

import com.jojoldu.book.springboot.domain.user.Role;
import com.jojoldu.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class NaverUser {
    private String name;
    private String email;
    private String profileImage;

    @Builder
    public NaverUser(String name, String email, String profileImage) {
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
    }

    public static NaverUser of(Map<String, Object> attributes) {
        return NaverUser.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .profileImage((String) attributes.get("profileImage"))
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(profileImage)
                .role(Role.GUEST)
                .build();
    }
}
