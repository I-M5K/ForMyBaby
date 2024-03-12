package com.ssafy.c202.formybaby.global.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CorsFilter corsFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //http.cors();

        //로그인페이지가 없는 api 프로젝트일 때 고정
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilter(corsFilter) // @CrossOrigin(인증X), 시큐리티 필터에 등록인증(O)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/login/oauth2/code/kakao").permitAll()
//                                .requestMatchers("/api/v1/auth/**").permitAll()
//                                .requestMatchers("/api/v1/verify/**").permitAll()
                                .anyRequest().authenticated());
        return http.build();
    }
}
