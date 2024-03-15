package com.ssafy.c202.formybaby.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.Arrays; // 필요한 import 추가

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true); // 서버가 응답할 때 json을 자바스크립트에서 처리할 수 있도록 설정
        config.addAllowedOrigin("*"); // 이 줄을 제거하거나 주석 처리
        config.setAllowedOriginPatterns(Arrays.asList("*")); // 모든 출처 허용 패턴 설정
        config.addAllowedHeader("*"); // 모든 헤더에 응답 허용
        config.addAllowedMethod("*"); // 모든 HTTP 메소드 요청 허용
        config.addExposedHeader("Authorization"); // "Authorization" 헤더 노출
        source.registerCorsConfiguration("/**", config); // 모든 경로에 대해 CORS 설정 적용
        return new CorsFilter(source);
    }
}
