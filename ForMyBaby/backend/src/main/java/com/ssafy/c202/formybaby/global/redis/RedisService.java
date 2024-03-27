package com.ssafy.c202.formybaby.global.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String, Map<String, LatLon>> locationRedisTemplate;

    public void setValue(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public String getValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    // JWT 토큰을 키로, userId를 값으로 저장하는 메서드
    public void saveUserIdByToken(String token, Long userId) {
        stringRedisTemplate.opsForValue().set(token, String.valueOf(userId));
    }

    // JWT 토큰에 해당하는 userId를 조회하는 메서드
    public String getUserIdByToken(String token) {
        return stringRedisTemplate.opsForValue().get(token);
    }

    // babyId을 키로, babyId 값을 저장하는 메서드
    public void saveBabyIdsByToken(String key, Long babyId) {
        log.info("savedBabyId : " + babyId);
        stringRedisTemplate.opsForValue().set(key, String.valueOf(babyId));
    }

    // babyName에 해당하는 babyId 조회하는 메서드
    public String getBabyIdByToken(String key) {
        log.info("getBabyIdByToken key : " + key);
        log.info("getBabyId : " + stringRedisTemplate.opsForValue().get(key));
        return stringRedisTemplate.opsForValue().get(key);
    }

    public Map<String, LatLon> setLocation(String familyCode, Long id, Double lat, Double lon) {
        if(locationRedisTemplate.opsForValue().get("location::"+familyCode) == null) {
            Map<String, LatLon> map = new HashMap<>();
            LatLon obj = new LatLon(lat, lon);
            map.put(String.valueOf(id), obj);
            locationRedisTemplate.opsForValue().set("location::"+familyCode, map);
        } else {
            locationRedisTemplate.opsForValue().get("location::"+familyCode)
                    .put(String.valueOf(id), new LatLon(lat, lon));
        }
        return locationRedisTemplate.opsForValue().get("location::"+familyCode);
    }
}
