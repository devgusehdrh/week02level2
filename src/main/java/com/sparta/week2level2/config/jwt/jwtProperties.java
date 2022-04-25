package com.sparta.week2level2.config.jwt;

public interface jwtProperties {
    String SECRET = "hanghae99";
    int EXPIRATION_TIME = 60000 * 30;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
