package com.ssafy.c202.formybaby.global.oauth2;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuth2Controller {

    @PostMapping("/login/oauth2/code/kakao")
    public String kakaoLogin(@RequestParam String code) {
        // authentication.getPrincipal()에서 사용자 정보를 가져올 수 있음
        // 필요에 따라 사용자 정보를 이용한 로직 구현
        System.out.println(code);
        return "카카오 로그인 성공";
    }
}
