package com.ssafy.c202.formybaby.user.service;

import com.ssafy.c202.formybaby.baby.service.BabyService;
import com.ssafy.c202.formybaby.global.jwt.JwtService;
import com.ssafy.c202.formybaby.global.redis.RedisService;
import com.ssafy.c202.formybaby.user.entity.Oauth;
import com.ssafy.c202.formybaby.user.entity.User;
import com.ssafy.c202.formybaby.user.repository.OauthRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
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
    private RedisService redisService;

    @Autowired
    private OauthRepository oauthRepository;

    @Autowired
    private RestTemplate restTemplate; // RestTemplate 의존성 주입

    @Override
    public Map<String, Object> getKakaoUserInfo(String accessToken) {
        try {
            // 카카오 사용자 정보 요청 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessToken); // Bearer 토큰으로 인증 방식 설정
            HttpEntity<?> entity = new HttpEntity<>(headers);

            // 카카오 사용자 정보 요청 URL
            String userInfoUri = "https://kapi.kakao.com/v2/user/me";

            // 사용자 정보 요청
            ResponseEntity<Map> response = restTemplate.exchange(userInfoUri, HttpMethod.GET, entity, Map.class);

            return response.getBody(); // 사용자 정보 반환
        } catch (RestClientException e) {
            // 통신 오류 등으로 예외가 발생한 경우
            log.error("Failed to fetch Kakao user info: " + e.getMessage());
            throw new RuntimeException("Failed to fetch Kakao user info");
        }
    }

    @Override
    public Oauth findByOauthId(Long oauthId){
        try {
            Oauth oauth = oauthRepository.findByOauthId(oauthId);
            return oauth;
        } catch (Exception e) {
            log.error("Error occurred while fetching oauth info by id: " + e.getMessage());
            throw new RuntimeException("Failed to fetch oauth info");
        }
    }


    // 로그인
    @Override
    public String processUserLogin(Map<String, Object> kakaoUserInfo) {
        log.info("로그인");

        String jwtToken;

        try {
            // jwt 생성
            jwtToken = jwtService.generateToken(Long.toString((Long) kakaoUserInfo.get("id")));
            log.info("jwtToken : " + jwtToken);

            User existingUser = userService.findByOauthId((Long)kakaoUserInfo.get("id"));

            log.info("existingUser : " + existingUser);

            // Redis에 JWT 토큰과 userId를 저장
            redisService.saveUserIdByToken(jwtToken, existingUser.getUserId());
        } catch (Exception e) {
            log.error("Error occurred during user login process: " + e.getMessage());
            throw new RuntimeException("User login failed");
        }
        return jwtToken;
    }


    @Override
    public String processUserRegistration(Map<String, Object> kakaoUserInfo) {
        log.info("회원가입");

        String jwtToken;

        try {
            Map<String, Object> kakaoAccount = (Map<String, Object>) kakaoUserInfo.get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

            Oauth oauth = new Oauth();
            oauth.setOauthId((Long) kakaoUserInfo.get("id"));
            oauth.setName((String) kakaoAccount.get("name")); // 카카오 계정으로부터 이름 추출
            oauth.setProfileImg((String) profile.get("thumbnail_image_url"));// 프로필 이미지 URL 추출
            oauth.setAccountEmail((String) kakaoAccount.get("email")); // 카카오 계정 이메일 추출

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

            // Redis에 JWT 토큰과 userId를 저장
            redisService.saveUserIdByToken(jwtToken, newUser.getUserId());

        } catch (Exception e) {
            log.error("Error occurred during user registration process: " + e.getMessage());
            throw new RuntimeException("User registration failed");
        }

        return jwtToken;
    }
}

