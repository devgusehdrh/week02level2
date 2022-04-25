package com.sparta.week2level2.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sparta.week2level2.config.auth.PrincipalDetails;
import com.sparta.week2level2.model.User;
import com.sparta.week2level2.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository){
        super(authenticationManager);
        this.userRepository = userRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("인증이 필요한 주소가 요청되었음");

        String jwtHeader = request.getHeader(jwtProperties.HEADER_STRING);
        System.out.println("jwtHeader: " + jwtHeader);

        // 헤더 존재여부 확인
        if(jwtHeader ==null || !jwtHeader.startsWith(jwtProperties.TOKEN_PREFIX)){
            chain.doFilter(request,response);
            return;
        }

        String jwtToken = request.getHeader("Authorization").replace(jwtProperties.TOKEN_PREFIX,"");

        String username =
                JWT.require(Algorithm.HMAC512(jwtProperties.SECRET)).build().verify(jwtToken).getClaim("username").asString();

        // 서명 완료
        if(username != null){
            User user = userRepository.findByUsername(username);

            PrincipalDetails principalDetails = new PrincipalDetails(user);

            // JWT 토큰 서명을 통해 서명이 정상이면 Authentication 객체 생성
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(principalDetails,null,principalDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request,response);

        }

    }
}
