package com.ssafy.c202.formybaby.user.service;

import com.ssafy.c202.formybaby.global.jpaEnum.Role;
import com.ssafy.c202.formybaby.global.redis.RedisService;
import com.ssafy.c202.formybaby.user.dto.request.UserUpdateRequest;
import com.ssafy.c202.formybaby.user.dto.response.UserProfileResponse;
import com.ssafy.c202.formybaby.user.dto.response.UserReadResponse;
import com.ssafy.c202.formybaby.user.entity.Family;
import com.ssafy.c202.formybaby.user.entity.Oauth;
import com.ssafy.c202.formybaby.user.entity.User;
import com.ssafy.c202.formybaby.user.repository.FamilyRepository;
import com.ssafy.c202.formybaby.user.repository.OauthRepository;
import com.ssafy.c202.formybaby.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final RedisService redisService;

    private final OauthRepository oauthRepository;

    private final FamilyRepository familyRepository;

    @Override
    public User findByUserId(Long userId) {
        User user = userRepository.findByUserId(userId);
        if(userId!=null){
            return user;
        }else{
            return null;
        }
    }

    @Override
    public ResponseEntity<UserReadResponse> findByUserReadResponseUserId(Long userId) {
        User user = userRepository.findByUserId(userId); // User 엔티티를 조회
        Family family = familyRepository.findFamilyByUser_UserId(userId);
        log.info("family : " + family);
        Role role = family.getRole();
        if (user != null) {
            UserReadResponse userReadResponse = new UserReadResponse(
                    user.getUserId(),
                    user.getOauth().getName(),
                    user.getOauth().getProfileImg(),
                    role
            );
            return new ResponseEntity<>(userReadResponse, HttpStatus.OK);
        } else {
            // 해당 userId를 가진 User가 없는 경우 null 반환
            return null;
        }
    }

//    @Override
//    public List<UserReadResponse> findByUserReadResponseListUserId(Long userId) {
//        User user = userRepository.findByUserId(userId);
//
//        return null;
//    }

    @Override
    public User findByOauthId(Long oauthId) {
        // 사용자를 찾음
        User user = userRepository.findByOauth_OauthId(oauthId);
        // 사용자가 없는 경우
        if (user == null) {
            // 예외를 발생시킴
            throw new RuntimeException("User not found for oauthId: " + oauthId);
        }
        // 사용자를 반환
        return user;
    }


    @Override
    public User registerUser(User user) {
        // 사용자가 이미 데이터베이스에 존재하는지 확인
        User existingUser = userRepository.findByOauth_OauthId(user.getOauth().getOauthId());
        if (existingUser != null) {
            // 이미 존재하는 사용자이므로 예외를 발생시킴
            throw new RuntimeException("User already exists");
        }
        // 사용자를 등록하고 저장
        user = userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(@RequestHeader(name = "Authorization") String token) {
        String redisGetUserId = redisService.getUserIdByToken(token);
        // Redis에서 가져온 값이 null이 아니고, 길이가 충분히 길다면
        if (redisGetUserId != null && redisGetUserId.length() >= 11) {
            // 마지막 11자리를 제외한 나머지를 id로 추정
            String idSubstring = redisGetUserId.substring(0, redisGetUserId.length() - 11);
            log.info("idSubstring : " + idSubstring);
            long userId = Long.parseLong(idSubstring);
            // userId를 사용하여 필요한 작업을 수행
            userRepository.deleteById(userId);
        } else {
            log.error("Redis에서 올바른 userId 값을 가져오지 못했습니다.");
        }
    }

    @Override
    public UserProfileResponse updateUser(UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findByUserId(userUpdateRequest.userId());
        Oauth oauth = oauthRepository.findByOauthId(user.getOauth().getOauthId());
        Family family = familyRepository.findFamilyByUser_UserId(userUpdateRequest.userId());
        Role role = family.getRole();
        if(user != null){
            UserProfileResponse userProfileResponse = new UserProfileResponse(
                    oauth.getName(),
                    oauth.getProfileImg(),
                    role
            );
            return userProfileResponse;
        }
        else{
            return null;
        }
    }

    @Override
    public String findFcmToken(Long userId) {
        try{
            String fcmToken = userRepository.findFcmTokenByUserId(userId);
            return fcmToken;
        }catch (Exception e){
            return null;
        }
    }
}
