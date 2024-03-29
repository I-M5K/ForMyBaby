package com.ssafy.c202.formybaby.sleep.service;

import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.baby.repository.BabyRepository;
import com.ssafy.c202.formybaby.global.redis.RedisService;
import com.ssafy.c202.formybaby.sleep.dto.response.SleepAllList;
import com.ssafy.c202.formybaby.sleep.entity.Danger;
import com.ssafy.c202.formybaby.sleep.entity.Sleep;
import com.ssafy.c202.formybaby.sleep.repository.DangerRepository;
import com.ssafy.c202.formybaby.sleep.repository.SleepRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SleepServiceImpl implements SleepService {

    private final SleepRepository sleepRepository;

    private final BabyRepository babyRepository;

    private final RedisService redisService;

    private final DangerRepository dangerRepository;

    @Override
    public SleepAllList getTodayAllList(String token) {
        Long babyId = Long.valueOf(redisService.getBabyIdByToken(redisService.getUserIdByToken(token)));
        List<Sleep> sleepList = sleepRepository.findAllByBaby_BabyIdOrderBySleepIdDesc(babyId);
        List<Danger> dangerList = dangerRepository.findAllByBaby_BabyIdOrderByCreatedAtDesc(babyId);

        // sleepList가 비어있지 않으면 첫 번째 Sleep 객체의 sleepTime을 가져옴
        int sleepTime = sleepList.isEmpty() ? 0 : sleepList.get(0).getSleepTime();

        // sleepList와 dangerList의 크기를 각각 가져옴
        int sleepCnt = sleepList.size();
        int dangerCnt = dangerList.size();

        // SleepAllList 객체 생성 후 반환
        return new SleepAllList(sleepTime, sleepCnt, dangerCnt);
    }

    @Override
    public void getSleepOnTime(String token, Timestamp createdAt) {
        log.info("createdAt : " + createdAt);
        String userId = redisService.getUserIdByToken(token);
        Long babyId = Long.valueOf(redisService.getBabyIdByToken(userId));
        Baby baby = babyRepository.findByBabyId(babyId);
        // 베이비 아이디로 수면 목록을 가져온다.
        List<Sleep> sleepList = sleepRepository.findAllByBaby_BabyIdOrderBySleepIdDesc(babyId);

        if (!sleepList.isEmpty()) {
            Sleep sleepCheck = sleepList.get(0);
            log.info("sleepList : " + sleepList);
            // 가장 최근의 잠든 시간과 잠든 횟수를 가져온다.
            Sleep sleep = new Sleep();
            sleep.setBaby(baby);
            sleep.setSleepCnt(sleepCheck.getSleepCnt());
            sleep.setSleepTime(sleepCheck.getSleepTime());
            sleep.setCreatedAt(createdAt);
            sleep.setEndAt(null);
            sleep.setSleepTime(sleepCheck.getSleepTime());
            sleepRepository.save(sleep);
        } else {
            Sleep sleep = new Sleep();
            sleep.setSleepCnt(0);
            sleep.setBaby(baby);
            sleep.setCreatedAt(createdAt);
            sleep.setEndAt(null);
            sleep.setSleepTime(0);
            sleepRepository.save(sleep);
        }
    }

    @Override
    public void getAwakeTimeList(String token, Timestamp endAt) {
        String userId = redisService.getUserIdByToken(token);
        Long babyId = Long.valueOf(redisService.getBabyIdByToken(userId));
        Baby baby = babyRepository.findByBabyId(babyId);

        // 베이비 아이디로 수면 목록을 가져온다.
        List<Sleep> sleepList = sleepRepository.findAllByBaby_BabyIdOrderBySleepIdDesc(babyId);

        if (!sleepList.isEmpty()) {
            // 가장 최근의 잠든 시간과 잠든 횟수를 가져온다.
            Sleep latestSleep = sleepList.get(0);
            Timestamp createdAt = latestSleep.getCreatedAt();
            int sleepCnt = latestSleep.getSleepCnt() + 1;

            // 가장 최근의 누적 시간을 가져온다.
            int currSleepTime = latestSleep.getSleepTime();

            // endAt와 createdAt의 차이를 분 단위로 계산
            long diffMillis = endAt.getTime() - createdAt.getTime();
            long diffMinutes = diffMillis / (60 * 1000);

            // 현재 누적 시간에 차이를 더해준다.
            currSleepTime += (int) diffMinutes;

            latestSleep.setSleepTime(currSleepTime);
            latestSleep.setBaby(baby);
            latestSleep.setSleepCnt(sleepCnt);
            latestSleep.setEndAt(endAt);
            sleepRepository.save(latestSleep);
        }
    }
}
