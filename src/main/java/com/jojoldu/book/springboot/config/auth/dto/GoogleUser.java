package com.jojoldu.book.springboot.config.auth.dto;

import com.jojoldu.book.springboot.domain.member.Member;
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

    public static GoogleUser of(Map<String, Object> authenticated) {
        return GoogleUser.builder()
                .principalId((String) authenticated.get("id"))
                .name((String) authenticated.get("name"))
                .email((String) authenticated.get("email"))
                .picture((String) authenticated.get("picture"))
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .principalId(principalId)
                .name(name)
                .email(email)
                .picture(picture)
                .build();
    }
}
