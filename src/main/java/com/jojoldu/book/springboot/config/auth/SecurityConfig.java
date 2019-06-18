package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOidcUserService customOidcUserService;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.MEMBER.name())
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .oidcUserService(customOidcUserService);
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        return customUserDetailsService;
    }
}