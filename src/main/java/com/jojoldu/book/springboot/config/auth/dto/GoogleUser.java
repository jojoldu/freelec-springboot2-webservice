package com.jojoldu.book.springboot.config.auth.dto;

import com.jojoldu.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class GoogleUser {
    private String principalId;
    private String name;
    private String email;
    private String picture;
    private String memo;

    @Builder
    public GoogleUser(String principalId, String name, String email, String picture, String memo) {
        this.principalId = principalId;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.memo = memo;
    }

    public static GoogleUser of(Map<String, Object> attributes) {
        return GoogleUser.builder()
                .principalId((String) attributes.get("id"))
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .build();
    }

    public User toEntity() {
        return User.builder()
                .principalId(principalId)
                .name(name)
                .email(email)
                .picture(picture)
                .build();
    }
}
