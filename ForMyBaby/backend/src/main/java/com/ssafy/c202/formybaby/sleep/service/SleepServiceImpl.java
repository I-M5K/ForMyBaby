package com.ssafy.c202.formybaby.sleep.service;

import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.baby.repository.BabyRepository;
import com.ssafy.c202.formybaby.global.redis.RedisService;
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
    public SleepWeekAllList getWeekAllList(String token, Timestamp endAtex) {
        Timestamp endAt = getCurrentTimestamp();
        log.info("endAt1 : " + endAt);
        // 주어진시간에 9시간을 더하고 하루를 뺌. 새로운 Timestamp 객체 생성
        Calendar setCalender = Calendar.getInstance();
        setCalender.setTimeInMillis(endAt.getTime());
        setCalender.add(Calendar.HOUR_OF_DAY, 9); // 9시간을 더함
        setCalender.add(Calendar.HOUR_OF_DAY, -24); // 24시간을 뺌
        endAt.setTime(setCalender.getTimeInMillis());
        log.info("endAt2 : " + endAt);

        Long babyId = Long.valueOf(redisService.getBabyIdByToken(redisService.getUserIdByToken(token)));
        //List<Sleep> sleepList = sleepRepository.findAllByBaby_BabyIdOrderBySleepIdDesc(babyId);
        //List<Danger> dangerList = dangerRepository.findAllByBaby_BabyIdOrderByCreatedAtDesc(babyId);




//        // Calendar 객체를 이용하여 endAt을 처리
//        Calendar endCalendar = Calendar.getInstance();
//        endCalendar.setTimeInMillis(endAt.getTime());
//
//        // endAt을 00:00:00으로 설정
        Calendar endTime = getCalendar(endAt);
        System.out.println("캘린더 형식" + endTime);
        Calendar original = endTime;
        endTime.add(Calendar.DAY_OF_MONTH, -6);
        int[] dangerList = new int[7];
        int[] hoursList = new int[7];
        int[] sleepList = new int[7];

        int cnt = 0;

        List<Sleep> sleepList1 = sleepRepository.findAllByBaby_BabyIdOrderBySleepIdDesc(babyId);

//        while (cnt < 7) {
//            List<Sleep> s = sleepRepository.findAllListByDate(endTime.getTime(), babyId);
//            if (!s.isEmpty()) {
//                sleepList[cnt] = s.get(s.size() - 1).getSleepCnt();
//                hoursList[cnt] = s.get(s.size() - 1).getSleepTime();
//                System.out.println("sleepList " + cnt + " " + sleepList[cnt]);
//                System.out.println("hoursList " + cnt + " " + hoursList[cnt]);
//            } else {
//                sleepList[cnt] = 0;
//                hoursList[cnt] = 0;
//            }
//            cnt++;
//            endTime.add(Calendar.DAY_OF_MONTH, 1);
//        }
//        System.out.println(sleepList.toString());
//        System.out.println(hoursList.toString());
//
//        original.add(Calendar.DAY_OF_MONTH, -6);
        cnt = 0;
        while (cnt < 7) {
            List<Danger> d = dangerRepository.findAllListByDate(original.getTime(), babyId);
            if (!d.isEmpty()) {
                dangerList[cnt] = d.get(d.size() - 1).getDangerCnt();
                System.out.println("dangerList " + cnt + " " + dangerList[cnt]);
            } else {
                dangerList[cnt] = 0;
            }
            hoursList[cnt] = sleepList1.get(cnt).getSleepTime();
            sleepList[cnt] = sleepList1.get(cnt).getSleepCnt();
            cnt++;
            original.add(Calendar.DAY_OF_MONTH, 1);
        }
        System.out.println(dangerList.toString());


//
//        Calendar[] date = new Calendar[7];
//        for (int i = 6; i >= 0; i++){
//
//        }
//        while (cnt < 7){
//            for (int i = 0; i < sleepList.size(); i++) {
//
//            }
//        }
//
//        // endAt의 7일 전 날짜 계산
//        //endTime.add(Calendar.DAY_OF_MONTH, -7);
//        Calendar prev = endTime;
//        boolean chk = false;
//        if (!sleepList.isEmpty()) {
//            for (int i = 0; i < sleepList.size(); i++) {
//                if (comp(getCalendar(sleepList.get(0).getCreatedAt()), endTime)){
//                    chk = true;
//                }
//                if (chk && getCalendar(sleepList.get(i).getCreatedAt()) != prev){
//                    prev = getCalendar(sleepList.get(i).getCreatedAt());
//                }
//            }
//        }
//
//
//        // 7일 전 날짜를 startDate로 설정
//        Timestamp startDate = new Timestamp(endCalendar.getTimeInMillis());
//
//        log.info("babyId : " + babyId);
//        log.info("startDate :" + startDate);
//        log.info("endAt :" + endAt);
//        // 일주일 동안의 수면 목록 조회
//        List<Sleep> sleepList = sleepRepository.findByWeekSleep(babyId, startDate, endAt);
//
//        log.info("sleepList : " + sleepList);
//
//        // 일주일 동안의 위험 목록 조회
//        List<Danger> dangerList = dangerRepository.findByBaby_BabyIdAndCreatedAt(babyId, startDate, endAt);
//
//        log.info("dangerList : " + dangerList);

        // SleepWeekAllList 객체 생성하여 반환
        return new SleepWeekAllList(sleepList, dangerList, hoursList);
    }




    @Override
    public SleepTodayAllList getTodayAllList(String token) {
        Long babyId = Long.valueOf(redisService.getBabyIdByToken(redisService.getUserIdByToken(token)));
        List<Sleep> sleepList = sleepRepository.findAllByBaby_BabyIdOrderBySleepIdDesc(babyId);
        List<Danger> dangerList = dangerRepository.findAllByBaby_BabyIdOrderByCreatedAtDesc(babyId);

        Sleep sleep = new Sleep();
        int sleepCnt = 0;
        int sleepTime = 0;
        int dangerCnt = 0;
        // sleepList가 비어있지 않으면 첫 번째 Sleep 객체를 가져옴.
        if(!sleepList.isEmpty()){
//            if (getCalendar(sleepList.get(0).getCreatedAt()) == getCalendar(getCurrentTimestamp())){
                sleepCnt = sleepList.get(0).getSleepCnt();
                sleepTime = sleepList.get(0).getSleepTime();
//            }
        }
        Danger danger = new Danger();
        // dangerList가 비어있찌 않으면 첫 번째 Danger 객체를 가져옴.
        if(!dangerList.isEmpty()){
//            if (getCalendar(dangerList.get(0).getCreatedAt()) == getCalendar(getCurrentTimestamp())){
                dangerCnt = dangerList.get(0).getDangerCnt();
//            }
        }

//        // 각각의 수면 시간,횟수, 위험 행동 횟수,  생성 시간들을 가져와서 저장한다.
//        Timestamp sleepCreatedAt = sleep.getCreatedAt();
//        Timestamp dangerCreatedAt = danger.getCreatedAt();

        // SleepAllList 객체 생성 후 반환
        return new SleepTodayAllList(sleepTime, sleepCnt, dangerCnt);
    }

    @Override
    public void getSleepOnTime(String token,Long babyId) {
        Timestamp createdAt = getCurrentTimestamp();
        log.info("createdAt1 : " + createdAt);
        // 주어진시간에 9시간을 더하고 하루를 뺌. 새로운 Timestamp 객체 생성
        Calendar setCalender = Calendar.getInstance();
        setCalender.setTimeInMillis(createdAt.getTime());
        setCalender.add(Calendar.HOUR_OF_DAY, 9); // 9시간을 더함
        setCalender.add(Calendar.HOUR_OF_DAY, -24); // 24시간을 뺌
        createdAt.setTime(setCalender.getTimeInMillis());
        log.info("createdAt2 : " + createdAt);

        // 유저 정보를 가져온다.
//        Long babyId = Long.valueOf(redisService.getBabyIdByToken(redisService.getUserIdByToken(token)));
        Baby baby = babyRepository.findByBabyId(babyId);

        // 주어진 시간의 년, 월, 일을 추출하여 날짜를 동일하게 만듭니다.
        Calendar createdCal = Calendar.getInstance();
        createdCal.setTimeInMillis(createdAt.getTime());
        int createdYear = createdCal.get(Calendar.YEAR);
        int createdMonth = createdCal.get(Calendar.MONTH);
        int createdDay = createdCal.get(Calendar.DAY_OF_MONTH);
        createdCal.clear();
        createdCal.set(createdYear, createdMonth, createdDay);

        // 베이비 아이디로 수면 목록을 가져옵니다.
        List<Sleep> sleepList = sleepRepository.findAllByBaby_BabyIdOrderBySleepIdDesc(babyId);

        // 수면 목록이 있다면
        if (!sleepList.isEmpty()) {
            Sleep latestSleep = sleepList.get(0);
            log.info("latestSleep : " + latestSleep);

            //최근 잠든 시간을 구한다.
            Calendar latestSleepCal = Calendar.getInstance();
            latestSleepCal.setTimeInMillis(latestSleep.getCreatedAt().getTime());
            int latestSleepYear = latestSleepCal.get(Calendar.YEAR);
            int latestSleepMonth = latestSleepCal.get(Calendar.MONTH);
            int latestSleepDay = latestSleepCal.get(Calendar.DAY_OF_MONTH);
            latestSleepCal.clear();
            latestSleepCal.set(latestSleepYear, latestSleepMonth, latestSleepDay);

            // 주어진 시간과 최근 잠든 시간의 날짜가 다르다면
            if (!latestSleepCal.equals(createdCal)) {
                // 새로운 수면 필드를 시간과 타임을 0으로 설정하여 생성합니다.
                Sleep sleep = new Sleep();
                sleep.setBaby(baby);
                sleep.setSleepCnt(0);
                sleep.setSleepTime(0);
                sleep.setCreatedAt(createdAt);
                sleepRepository.save(sleep);
            //만약 주어진 시간과 최근 잠든 시간의 날짜가 같다면
            } else {
                // 최근 잠든 시간 정보를 새로운 슬립에 담구 createdAt만 갱신해준다.
                Sleep sleep = new Sleep();
                sleep.setBaby(baby);
                sleep.setSleepCnt(latestSleep.getSleepCnt());
                sleep.setSleepTime(latestSleep.getSleepTime());
                sleep.setCreatedAt(createdAt);
                sleep.setEndAt(null);
                sleepRepository.save(sleep);
            }
        // 수면 목록이 없다면 새로운 수면 필드를 생성해줍니다. 이 때 시간과 타임은 0으로 설정합니다.
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
    public void getAwakeOnTime(String token, Long babyId) {
        Timestamp endAt = getCurrentTimestamp();
        log.info("endAt1 : " + endAt);
        // 주어진시간에 9시간을 더하고 하루를 뺌. 새로운 Timestamp 객체 생성
        Calendar setCalender = Calendar.getInstance();
        setCalender.setTimeInMillis(endAt.getTime());
        setCalender.add(Calendar.HOUR_OF_DAY, 9); // 9시간을 더함
        setCalender.add(Calendar.HOUR_OF_DAY, -24); // 24시간을 뺌
        endAt.setTime(setCalender.getTimeInMillis());
        log.info("endAt2 : " + endAt);
        //Long babyId = Long.valueOf(redisService.getBabyIdByToken(redisService.getUserIdByToken(token)));
        Baby baby = babyRepository.findByBabyId(babyId);

        // 베이비 아이디로 수면 목록을 가져옵니다.
        List<Sleep> sleepList = sleepRepository.findAllByBaby_BabyIdOrderBySleepIdDesc(babyId);

        // 수면 목록이 있다면
        if (!sleepList.isEmpty()) {
            // 가장 최근의 잠든 시간과 잠든 횟수를 가져옵니다.
            Sleep latestSleep = sleepList.get(0);
            Timestamp createdAt = latestSleep.getCreatedAt();
            int sleepCnt = latestSleep.getSleepCnt();


            Calendar endAtCal = Calendar.getInstance();
            endAtCal.setTimeInMillis(endAt.getTime());
            int endAtYear = endAtCal.get(Calendar.YEAR);
            int endAtMonth = endAtCal.get(Calendar.MONTH);
            int endAtDay = endAtCal.get(Calendar.DAY_OF_MONTH);
            endAtCal.clear();
            endAtCal.set(endAtYear, endAtMonth, endAtDay);

            Calendar createdAtCal = Calendar.getInstance();
            createdAtCal.setTimeInMillis(createdAt.getTime());
            int createdAtYear = createdAtCal.get(Calendar.YEAR);
            int createdAtMonth = createdAtCal.get(Calendar.MONTH);
            int createdAtDay = createdAtCal.get(Calendar.DAY_OF_MONTH);
            createdAtCal.clear();
            createdAtCal.set(createdAtYear, createdAtMonth, createdAtDay);

            // 주어진 endAt와 createdAt의 날짜가 다를 경우
            if (!endAtCal.equals(createdAtCal)) {
                log.info("날짜가 다름");
                // 시간의 차이를 분 단위로 계산하여 time에 추가하고 cnt를 1 증가시킵니다.
                long diffMillis = endAt.getTime() - createdAt.getTime();
                long diffMinutes = diffMillis / (60 * 1000);
                int currSleepTime = latestSleep.getSleepTime() + (int) diffMinutes;

                //현재 목록에 시간과 횟수를 갱신해서 넣고
                latestSleep.setSleepTime(currSleepTime);
                latestSleep.setSleepCnt(sleepCnt+1);
                sleepRepository.save(latestSleep);

                // 새로운 슬립에 시간과 횟수를 0으로 설정합니다.
                Sleep sleep = new Sleep();
                sleep.setSleepCnt(0);
                sleep.setBaby(baby);
                sleep.setCreatedAt(createdAt);
                sleep.setEndAt(null);
                sleep.setSleepTime(0);
                sleepRepository.save(sleep);

            } else {
                log.info("날짜까 같음");
                // 날짜가 같은 경우에는 시간의 차이만큼 time을 증가시킵니다.
                long diffMillis = endAt.getTime() - createdAt.getTime();
                long diffMinutes = diffMillis / (60 * 1000);
                int currSleepTime = latestSleep.getSleepTime() + (int) diffMinutes;

                //현재 목록만 갱신합니다.
                latestSleep.setSleepTime(currSleepTime);
                latestSleep.setSleepCnt(sleepCnt+1);
                latestSleep.setBaby(baby);
                latestSleep.setEndAt(endAt);
                sleepRepository.save(latestSleep);
            }

        }
    }
    public Timestamp getCurrentTimestamp() {
        // 현재 시간을 밀리초로 가져옴
        long currentTimeMillis = System.currentTimeMillis();
        // 밀리초를 Timestamp 객체로 변환하여 반환
        return new Timestamp(currentTimeMillis);
    }
    public Calendar getCalendar (Timestamp t){
        // 주어진 시간의 년, 월, 일을 추출하여 날짜를 동일하게 만듭니다.
        Calendar createdCal = Calendar.getInstance();
        createdCal.setTimeInMillis(t.getTime());
        int createdYear = createdCal.get(Calendar.YEAR);
        int createdMonth = createdCal.get(Calendar.MONTH);
        int createdDay = createdCal.get(Calendar.DAY_OF_MONTH);
        createdCal.clear();
        createdCal.set(createdYear, createdMonth, createdDay);
        return createdCal;
    }

    public static boolean comp(Calendar cal1, Calendar cal2) {
        return cal1.compareTo(cal2) <= 0;
    }
}
