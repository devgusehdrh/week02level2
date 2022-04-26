package com.sparta.week2level2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {


//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true); // 내서버가 응답할 때 json을 자바스크립트에서 처리할 수 있게할지 설정
//
//        // setAllowCredentials(true) 와 addAllowedOrigin("*") 를 함께 사용하지 못하게
//        // 업그레이드 되었으므로 addAllowedOriginPattern("*")를 사용하였다.
//        // 출처: https://kim6394.tistory.com/273
//        config.addAllowedOriginPattern("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//
//        config.addExposedHeader("Authorization");
//        source.registerCorsConfiguration("/api/**", config);
//        return new CorsFilter(source);
//    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true); // 내서버가 응답할 때 json을 자바스크립트에서 처리할 수 있게할지 설정
        // setAllowCredentials(true) 와 addAllowedOrigin("*") 를 함께 사용하지 못하게
        // 업그레이드 되었으므로 addAllowedOriginPattern("*")를 사용하였다.
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
