package com.ssafy.c202.formybaby.global.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setValue(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public String getValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    // JWT 토큰을 키로, userId를 값으로 저장하는 메서드
    public void saveUserIdByToken(String token, Long userId){
        stringRedisTemplate.opsForValue().set(token, String.valueOf(userId));
    }

    // JWT 토큰에 해당하는 userId를 조회하는 메서드
    public String getUserIdByToken(String token) {
        return stringRedisTemplate.opsForValue().get(token);
    }
}

