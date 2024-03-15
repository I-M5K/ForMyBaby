package com.ssafy.c202.formybaby.user.service;

import com.ssafy.c202.formybaby.global.jwt.JwtService;
import com.ssafy.c202.formybaby.user.entity.Oauth;
import com.ssafy.c202.formybaby.user.entity.User;
import com.ssafy.c202.formybaby.user.repository.OauthRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Slf4j
public class OAuthServiceImpl implements OAuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private OauthRepository oauthRepository;

    @Autowired
    private RestTemplate restTemplate; // RestTemplate 의존성 주입

    @Override
    public Map<String, Object> getKakaoUserInfo(String accessToken) {
        // 카카오 사용자 정보 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken); // Bearer 토큰으로 인증 방식 설정
        HttpEntity<?> entity = new HttpEntity<>(headers);

        // 카카오 사용자 정보 요청 URL
        String userInfoUri = "https://kapi.kakao.com/v2/user/me";

        // 사용자 정보 요청
        ResponseEntity<Map> response = restTemplate.exchange(userInfoUri, HttpMethod.GET, entity, Map.class);

        return response.getBody(); // 사용자 정보 반환
    }

    @Override
    public String processUserLoginOrRegistration(Map<String, Object> kakaoUserInfo) {
        // DB에서 사용자 조회 (가정)
        User user = userService.findByUserId((Long) kakaoUserInfo.get("id"));

        String jwtToken;

        if(user == null){
            log.info("user 값 없음");

            Map<String, Object> kakaoAccount = (Map<String, Object>) kakaoUserInfo.get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

            Oauth oauth = new Oauth();
            oauth.setId((Long) kakaoUserInfo.get("id"));
            oauth.setName((String) kakaoAccount.get("name")); // 카카오 계정으로부터 이름 추출
            oauth.setProfileImg((String) profile.get("profile_image_url")); // 프로필 이미지 URL 추출
            oauth.setAccountEmail((String) kakaoAccount.get("email")); // 카카오 계정 이메일 추출

            log.info("oauth : " + oauth);

            oauthRepository.save(oauth);

            // 유저 정보 저장 로직 추가 (유저 회원가입)
            User newUser = new User();
            newUser.setOauth(oauth); // Oauth 객체와의 연결 설정
            newUser.setDanger(false); // 기본값 설정 예시
            newUser.setGeneral(false); // 기본값 설정 예시
            newUser.setSound(false); // 기본값 설정 예시
            newUser.setUserState(false); // 기본값 설정 예시

            userService.registerUser(newUser); // 새 유저 정보 저장

            // jwt 생성
            jwtToken = jwtService.generateToken(Long.toString((Long) kakaoUserInfo.get("id")));
            log.info("jwtToken : " + jwtToken);
            return jwtToken;

        }else {
            log.info("user 값 있음");
            // 사용자 정보가 있으면 JWT 생성
            jwtToken = jwtService.generateToken(Long.toString((Long) kakaoUserInfo.get("id")));
            log.info("jwtToken : " + jwtToken);
            return jwtToken;
        }
    }
}

