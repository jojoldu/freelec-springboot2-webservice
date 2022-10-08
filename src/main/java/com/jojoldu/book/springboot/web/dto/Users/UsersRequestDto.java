package com.jojoldu.book.springboot.web.dto.Users;

import com.jojoldu.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersSaveRequestDto {
    private String name;
    private String password;

    @Builder
    public SaveRequestDto(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Users toEntity() {
        return Users.builder()
                .name(name)
                .password(password)
                .build();
    }
}

