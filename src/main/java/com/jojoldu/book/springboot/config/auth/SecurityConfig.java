package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.http.HttpMethod.POST;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private OidcUserService oidcUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        http
                .authorizeRequests()
                .antMatchers("/", "/login/oauth2/**", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers(POST, "/api/v1/**").hasAnyRole(Role.USER.name())
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable()
                .and()
                .formLogin()
                .successForwardUrl("/")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .csrf().disable()
//                .oauth2Client()
//                .and()
                .oauth2Login()
                .loginPage("/")
                .userInfoEndpoint()
                .oidcUserService(this.oidcUserService);
    }
}