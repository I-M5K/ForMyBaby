package com.ssafy.c202.formybaby.stopmotion.service;

import com.ssafy.c202.formybaby.global.redis.RedisService;
import com.ssafy.c202.formybaby.stopmotion.entity.StopMotion;
import com.ssafy.c202.formybaby.stopmotion.repository.StopMotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StopMotionServiceImpl implements StopMotionService {

    private final StopMotionRepository stopMotionRepository;

    private final RedisService redisService;

    @Override
    public String findMotionUrlByBabyId(String token) {
        Long babyId = Long.valueOf(redisService.getBabyIdByToken(redisService.getUserIdByToken(token)));
        Optional<StopMotion> stopmotionOptional = stopMotionRepository.findByBaby_BabyId(babyId);
        if (stopmotionOptional.isPresent()) {
            return stopmotionOptional.get().getMotionUrl();
        } else {
            throw new RuntimeException("해당 baby_id로 등록된 Stopmotion영상이 없습니다.");
        }
    }

    @Override
    public int countImagesByBabyId(String token) {
        Long babyId = Long.valueOf(redisService.getBabyIdByToken(redisService.getUserIdByToken(token)));
        return stopMotionRepository.countByBaby_BabyId(babyId);
    }
}