package com.sparta.week2level2.config;

import com.sparta.week2level2.config.jwt.JwtAuthenticationFilter;
import com.sparta.week2level2.config.jwt.JwtAuthorizationFilter;
import com.sparta.week2level2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsUtils;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;
    @Autowired
    private CorsConfig corsConfig;

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        // h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
        web
                .ignoring()
                .antMatchers("/h2-console/**");
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/templates");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                    .addFilter(corsConfig.corsFilter()) // cors 필터 정책 추가
                    .csrf().disable()
//                    .cors().configurationSource(corsConfig.corsConfigurationSource()).and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 방식 미사용

                .and()
                    .formLogin().disable() // login form 미사용
                    .httpBasic().disable() // id,pw 방식 미사용
                    .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                    .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
                    .authorizeRequests()
                        .antMatchers(HttpMethod.GET,"/api/posts").permitAll()
                        .antMatchers(HttpMethod.POST,"/api/login").permitAll()
                        .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
                        .antMatchers(HttpMethod.POST,"/api/register").permitAll()
                        .antMatchers("/api/**").authenticated()
                        .anyRequest().permitAll();

    }
}
