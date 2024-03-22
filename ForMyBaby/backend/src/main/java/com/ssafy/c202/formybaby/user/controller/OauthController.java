package com.ssafy.c202.formybaby.user.controller;

import com.ssafy.c202.formybaby.global.jwt.JwtProperties;
import com.ssafy.c202.formybaby.global.redis.RedisService;
import com.ssafy.c202.formybaby.user.dto.response.UserReadResponse;
import com.ssafy.c202.formybaby.user.entity.Oauth;
import com.ssafy.c202.formybaby.user.entity.User;
import com.ssafy.c202.formybaby.user.mapper.UserInfoMapper;
import com.ssafy.c202.formybaby.user.repository.OauthRepository;
import com.ssafy.c202.formybaby.user.repository.UserRepository;
import com.ssafy.c202.formybaby.user.service.OAuthService;
import com.ssafy.c202.formybaby.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("v1/oauth")
@Slf4j
public class OauthController {

    @Autowired
    private OAuthService oAuthService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RestTemplate restTemplate; // RestTemplate 의존성 주입

    @Value("${spring.oauth.kakao.client_id}")
    private String clientId; // application.properties에서 설정한 카카오 클라이언트 ID

    @Value("${spring.oauth.kakao.redirect_uri}")
    private String redirectUri; // 카카오로부터 인증 코드를 받을 리다이렉트 URI

    @Value("${spring.oauth.kakao.client_secret}")
    private String clientSecret; // 클라이언트 시크릿, 필요한 경우 사용
    
    @PostMapping("/kakao/login")
    public ResponseEntity<?> requestKakaoAccessToken(@RequestParam("code") String code) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code"); // 권한 부여 타입으로 'authorization_code' 지정
        params.add("client_id", clientId); // 클라이언트 ID 추가
        params.add("redirect_uri", redirectUri); // 리다이렉트 URI 추가
        params.add("code", code); // 카카오로부터 받은 인증 코드 추가
        params.add("client_secret", clientSecret); // 클라이언트 시크릿 추가, 필요한 경우만

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, requestHeaders);
        String tokenRequestUri = "https://kauth.kakao.com/oauth/token"; // 토큰 요청을 위한 카카오 URL

        // 카카오 토큰 요청
        ResponseEntity<Map> response = restTemplate.postForEntity(tokenRequestUri, request, Map.class);
        String accessToken = (String)response.getBody().get("access_token"); // 액세스 토큰 추출

        // 카카오에서 가져온 토큰 값으로 유저 정보 조회
        Map<String, Object> kakaoUserInfo = oAuthService.getKakaoUserInfo(accessToken);

        log.info("kakaoUserInfo : " + kakaoUserInfo);

        Long oauthId = (Long) kakaoUserInfo.get("id");

        log.info("oauthId : " + oauthId);

        // DB에서 사용자 조회 (가정)
        Oauth oauthUser = oAuthService.findByOauthId(oauthId);

        log.info("oauthUser : " + oauthUser);

        // 이미 있어서 로그인만 시킨다면
        if(oauthUser!=null){
            log.info("login");
            // 사용자 검증 및 JWT 토큰 생성
            String jwtToken = oAuthService.processUserLogin(kakaoUserInfo);

            String getUserId = redisService.getUserIdByToken(jwtToken);
            ResponseEntity<UserReadResponse> user = userService.findByUserReadResponseUserId(Long.valueOf(getUserId));
            kakaoUserInfo.put("userId",user.getBody().userId());

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
            return new ResponseEntity<>(kakaoUserInfo, responseHeaders, HttpStatus.OK);
        }
        // 없어서 새로 회원 가입을 시킨다면
        else{
            log.info("register");
            // 사용자 검증 및 JWT 토큰 생성
            String jwtToken = oAuthService.processUserRegistration(kakaoUserInfo);

            String getUserId = redisService.getUserIdByToken(jwtToken);
            ResponseEntity<UserReadResponse> user = userService.findByUserReadResponseUserId(Long.valueOf(getUserId));
            kakaoUserInfo.put("userId",user.getBody().userId());

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
            return new ResponseEntity<>(kakaoUserInfo, responseHeaders, HttpStatus.OK);
        }
    }
}
