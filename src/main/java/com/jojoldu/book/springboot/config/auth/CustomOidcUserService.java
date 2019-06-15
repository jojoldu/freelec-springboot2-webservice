package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.config.auth.dto.GoogleUser;
import com.jojoldu.book.springboot.domain.user.User;
import com.jojoldu.book.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomOidcUserService extends OidcUserService {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        GoogleUser googleUser = GoogleUser.of(oidcUser.getAttributes());
        saveOrUpdate(googleUser);
        httpSession.setAttribute("user", googleUser);
        return oidcUser;
    }

    private void saveOrUpdate(GoogleUser googleUser) {
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
