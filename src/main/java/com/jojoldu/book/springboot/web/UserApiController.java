package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.domain.user.User;
import com.jojoldu.book.springboot.service.UsersService;
import com.jojoldu.book.springboot.web.dto.Posts.PostsListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UsersService usersService;

    @GetMapping("/api/v1/users/{email}")
    public User findUserByEmail(@PathVariable String email) {
        System.out.println("email = " + email);
        return usersService.findByEmail(email);
    }

    @GetMapping("/api/v1/users")
    public List<User> findUserAll() {
        return usersService.findUserAll();
    }
}
