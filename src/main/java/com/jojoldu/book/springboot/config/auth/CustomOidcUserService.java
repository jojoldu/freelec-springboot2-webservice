package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.config.auth.dto.GoogleUser;
import com.jojoldu.book.springboot.domain.user.User;
import com.jojoldu.book.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOidcUserService extends OidcUserService {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        GoogleUser googleUser = GoogleUser.of(oidcUser.getAttributes());
        User user = saveOrUpdate(googleUser);
        httpSession.setAttribute("user", user);

        return new DefaultOidcUser(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                oidcUser.getIdToken(),
                oidcUser.getUserInfo());
    }

    private User saveOrUpdate(GoogleUser googleUser) {
        User user = userRepository.findByEmail(googleUser.getEmail())
                .map(entity -> entity.update(googleUser.getName(), googleUser.getPicture()))
                .orElse(googleUser.toEntity());

        return userRepository.save(user);
    }
}
