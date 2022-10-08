package com.jojoldu.book.springboot.service;

import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UsersService {
    private final UserRepository userRepository;

    @Transactional
    public Long save(UsersSaveRequestDto requestDto) {
        return userRepository.save(requestDto.toEntity()).getId();
    }
}
