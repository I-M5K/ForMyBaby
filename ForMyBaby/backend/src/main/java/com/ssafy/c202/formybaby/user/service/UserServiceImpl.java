package com.ssafy.c202.formybaby.user.service;

import com.ssafy.c202.formybaby.global.redis.RedisService;
import com.ssafy.c202.formybaby.user.dto.response.UserReadResponse;
import com.ssafy.c202.formybaby.user.entity.User;
import com.ssafy.c202.formybaby.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final RedisService redisService;

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

        if (user != null) {
            UserReadResponse userReadResponse = new UserReadResponse(
                    user.getUserId(),
                    user.getOauth().getName(),
                    user.getOauth().getProfileImg()
            );
            return new ResponseEntity<>(userReadResponse, HttpStatus.OK);
        } else {
            // 해당 userId를 가진 User가 없는 경우 null 반환
            return null;
        }
    }

    @Override
    public User findByOauthId(Long oauthId) {
        User user = userRepository.findByOauth_OauthId(oauthId);
        log.info("user2222 : " + user);
        return user;
    }

    @Override
    public User registerUser(User user) {
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
}
