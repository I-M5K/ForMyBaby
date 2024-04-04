package com.ssafy.c202.formybaby.stopmotion.service;

import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.baby.repository.BabyRepository;
import com.ssafy.c202.formybaby.global.redis.RedisService;
import com.ssafy.c202.formybaby.stopmotion.entity.StopMotion;
import com.ssafy.c202.formybaby.stopmotion.repository.StopMotionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class StopMotionServiceImpl implements StopMotionService {

    private final StopMotionRepository stopMotionRepository;

    private final BabyRepository babyRepository;

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
        return stopMotionRepository.countByBaby_BabyIdOrderByCreatedAtDesc(babyId);
    }

    @Override
    public void createStopMotionImage(String token, String imageUrl) {
        Long babyId = Long.valueOf(redisService.getBabyIdByToken(redisService.getUserIdByToken(token)));
        Optional<StopMotion> stopMotion = stopMotionRepository.findFirstByBaby_BabyIdOrderByCreatedAtDesc(babyId);
        Timestamp timestamp = getCurrentTimestamp();
        log.info("timestamp1 : " + timestamp);
        // 주어진시간에 9시간을 더하고 하루를 뺌. 새로운 Timestamp 객체 생성
        Calendar setCalender = Calendar.getInstance();
        setCalender.setTimeInMillis(timestamp.getTime());
        setCalender.add(Calendar.HOUR_OF_DAY, 9); // 9시간을 더함
        setCalender.add(Calendar.HOUR_OF_DAY, -24); // 24시간을 뺌
        timestamp.setTime(setCalender.getTimeInMillis());
        log.info("timestamp2 : " + timestamp);
        Baby baby = babyRepository.findByBabyId(babyId);
        if(!stopMotion.isPresent()){
            StopMotion stopMotion1 = new StopMotion();
            stopMotion1.setBaby(baby);
            stopMotion1.setMotionUrl(imageUrl);
            stopMotion1.setCount(1);
            stopMotion1.setCreatedAt(timestamp);
            stopMotionRepository.save(stopMotion1);
        }
        else{
            StopMotion stopMotion1 = new StopMotion();
            stopMotion1.setBaby(baby);
            stopMotion1.setMotionUrl(imageUrl);
            stopMotion1.setCount(stopMotion.get().getCount()+1);
            stopMotion1.setCreatedAt(timestamp);
            stopMotionRepository.save(stopMotion1);
        }
    }

    // 현재 시간을 Timestamp 객체로 가져오는 메서드
    public Timestamp getCurrentTimestamp() {
        // 현재 시간을 밀리초로 가져옴
        long currentTimeMillis = System.currentTimeMillis();
        // 밀리초를 Timestamp 객체로 변환하여 반환
        return new Timestamp(currentTimeMillis);
    }
}