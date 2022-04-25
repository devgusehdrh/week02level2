package com.sparta.week2level2.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.week2level2.config.auth.PrincipalDetails;
import com.sparta.week2level2.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

// /login 요청시 username, password 전송시 아래 필터가 동작함
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
        super.setAuthenticationManager(authenticationManager);
        super.setFilterProcessesUrl("/api/login");
    }

    
    // /login 요청시 로그인 시도를 위해 실행
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("========로그인 시도중========");
        try{
            ObjectMapper om = new ObjectMapper();
            User user = om.readValue(request.getInputStream(),User.class);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPw());

            Authentication authentication =
                    authenticationManager.authenticate(authenticationToken);

//            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
//            System.out.println(principalDetails.getUser().getUsername());
            return authentication;

        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("==========================");

        return null;
    }

    // 로그인 성공시 JWT 토큰 생성
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        System.out.println("=======인증완료=======");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        String jwtToken = JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt((new Date(System.currentTimeMillis()+jwtProperties.EXPIRATION_TIME)))
                .withClaim("id",principalDetails.getUser().getId())
                .withClaim("username",principalDetails.getUser().getUsername())
                .sign(Algorithm.HMAC512(jwtProperties.SECRET));

        response.addHeader("Authorization",jwtProperties.TOKEN_PREFIX+jwtToken);
    }

}
