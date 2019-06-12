package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.config.auth.dto.GoogleUser;
import com.jojoldu.book.springboot.domain.user.User;
import com.jojoldu.book.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        saveOrUpdate(oAuth2User);
        httpSession.setAttribute("user", oAuth2User);
        return oAuth2User;
    }

    private void saveOrUpdate(OAuth2User oAuth2User) {
        GoogleUser googleUser = GoogleUser.of(oAuth2User.getAttributes());
        Optional<User> userOptional = userRepository.findByEmail(googleUser.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.update(googleUser.getName(), googleUser.getPicture());
            userRepository.save(user);
        } else {
            userRepository.save(googleUser.toEntity());
        }
    }
}