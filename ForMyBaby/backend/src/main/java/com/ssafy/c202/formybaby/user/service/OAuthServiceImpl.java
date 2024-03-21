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
import java.util.Random;

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
        Oauth oauthUser = oauthRepository.findByOauthId((Long) kakaoUserInfo.get("id"));

        String jwtToken;

        if(oauthUser == null){
            log.info("oauthUser 값 없음");

            Map<String, Object> kakaoAccount = (Map<String, Object>) kakaoUserInfo.get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

            Oauth oauth = new Oauth();
            oauth.setOauthId((Long) kakaoUserInfo.get("id"));
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

            Random random = new Random();

            int randomNum = random.nextInt(10000); // 0부터 9999까지의 랜덤 정수 생성
            System.out.println(randomNum);

            // userId 생성: oauthId와 난수를 결합
            String userIdStr = oauth.getOauthId().toString() + String.format("%04d", randomNum);

            // Long 타입으로 변환을 시도합니다. 여기서는 변환에 실패할 가능성에 대한 처리가 필요합니다.
            Long userId = Long.parseLong(userIdStr);

            newUser.setUserId(userId);

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

