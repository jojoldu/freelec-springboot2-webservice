package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.member.Role;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        http
                .authorizeRequests()
                .antMatchers("/", "/login/oauth2/**", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers(POST, "/api/v1/**").hasAnyRole(Role.USER.name())
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .loginPage("/")
                .and()
                .headers().frameOptions().disable()
                .and()
                .exceptionHandling()
                .and()
                .formLogin()
                .successForwardUrl("/")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .csrf().disable();
    }
}