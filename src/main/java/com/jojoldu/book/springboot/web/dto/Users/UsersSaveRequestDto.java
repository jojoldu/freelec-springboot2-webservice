package com.jojoldu.book.springboot.web.dto.Users;

import com.jojoldu.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersSaveRequestDto {
    private String name;

    private String email;
    private String password;

    @Builder
    public UsersSaveRequestDto(String name, String email, String password) {
        this.name = name;
        this.email= email;
        this.password = password;
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}

