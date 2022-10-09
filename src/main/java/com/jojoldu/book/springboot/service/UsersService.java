package com.jojoldu.book.springboot.service;

import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.domain.user.User;
import com.jojoldu.book.springboot.domain.user.UserRepository;
import com.jojoldu.book.springboot.web.dto.Users.UsersSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UsersService {
    private final UserRepository userRepository;

    @Transactional
    public Long save(UsersSaveRequestDto requestDto) {
        return userRepository.save(requestDto.toEntity()).getId();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. email=" + email));
    }

    public List<User> findUserAll() {
        return userRepository.findUserAll();
    }
}
