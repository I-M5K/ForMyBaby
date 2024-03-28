package com.ssafy.c202.formybaby.sleep.service;

import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.baby.repository.BabyRepository;
import com.ssafy.c202.formybaby.global.redis.RedisService;
import com.ssafy.c202.formybaby.sleep.dto.response.SleepAllList;
import com.ssafy.c202.formybaby.sleep.dto.response.SleepTodayAllList;
import com.ssafy.c202.formybaby.sleep.dto.response.SleepWeekAllList;
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
    public SleepWeekAllList getWeekAllList(String token, Timestamp endAt) {
        Long babyId = Long.valueOf(redisService.getBabyIdByToken(redisService.getUserIdByToken(token)));

        // Calendar 객체를 이용하여 endAt을 처리
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTimeInMillis(endAt.getTime());

        // endAt을 00:00:00으로 설정
        endCalendar.set(Calendar.HOUR_OF_DAY, 0);
        endCalendar.set(Calendar.MINUTE, 0);
        endCalendar.set(Calendar.SECOND, 0);
        endCalendar.set(Calendar.MILLISECOND, 0);

        // endAt의 7일 전 날짜 계산
        endCalendar.add(Calendar.DAY_OF_MONTH, -7);

        // 7일 전 날짜를 startDate로 설정
        Timestamp startDate = new Timestamp(endCalendar.getTimeInMillis());

        // 일주일 동안의 수면 목록 조회
        List<Sleep> sleepList = sleepRepository.findByWeekSleep(babyId, startDate, endAt);

        log.info("sleepList : " + sleepList);

        // 일주일 동안의 위험 목록 조회
        List<Danger> dangerList = dangerRepository.findByBaby_BabyIdAndCreatedAt(babyId, startDate, endAt);

        log.info("dangerList : " + dangerList);

        // SleepAllList 객체를 담을 리스트 생성
        List<SleepAllList> sleepAllLists = new ArrayList<>();

        // 수면 목록과 위험 목록을 SleepAllList에 추가
        for (Timestamp date = startDate; date.getTime() <= endAt.getTime(); date.setTime(date.getTime() + 24 * 60 * 60 * 1000)) {
            Sleep sleep = findSleepByDate(sleepList, date);
            Danger danger = findDangerByDate(dangerList, date);
            sleepAllLists.add(new SleepAllList(sleep, danger));
        }

        // SleepWeekAllList 객체 생성하여 반환
        return new SleepWeekAllList(sleepAllLists);
    }

    // 주어진 날짜에 해당하는 수면 정보 찾기
    private Sleep findSleepByDate(List<Sleep> sleepList, Timestamp date) {
        for (Sleep sleep : sleepList) {
            if (isSameDate(sleep.getCreatedAt(), date)) {
                return sleep;
            }
        }
        return null;
    }

    // 주어진 날짜에 해당하는 위험 정보 찾기
    private Danger findDangerByDate(List<Danger> dangerList, Timestamp date) {
        for (Danger danger : dangerList) {
            if (isSameDate(danger.getCreatedAt(), date)) {
                return danger;
            }
        }
        return null;
    }

    // 두 Timestamp 객체가 같은 날짜인지 확인
    private boolean isSameDate(Timestamp timestamp1, Timestamp timestamp2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(timestamp1.getTime());
        Calendar cal2 = Calendar.getInstance();
        cal2.setTimeInMillis(timestamp2.getTime());
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }


    @Override
    public SleepTodayAllList getTodayAllList(String token) {
        Long babyId = Long.valueOf(redisService.getBabyIdByToken(redisService.getUserIdByToken(token)));
        List<Sleep> sleepList = sleepRepository.findAllByBaby_BabyIdOrderBySleepIdDesc(babyId);
        List<Danger> dangerList = dangerRepository.findAllByBaby_BabyIdOrderByCreatedAtDesc(babyId);

        // sleepList가 비어있지 않으면 첫 번째 Sleep 객체의 sleepTime을 가져옴
        int sleepTime = sleepList.isEmpty() ? 0 : sleepList.get(0).getSleepTime();

        // sleepList와 dangerList의 크기를 각각 가져옴
        int sleepCnt = sleepList.size();
        int dangerCnt = dangerList.size();

        // SleepAllList 객체 생성 후 반환
        return new SleepTodayAllList(sleepTime, sleepCnt, dangerCnt);
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
