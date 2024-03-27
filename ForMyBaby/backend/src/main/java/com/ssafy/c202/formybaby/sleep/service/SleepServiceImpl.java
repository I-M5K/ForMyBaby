package com.ssafy.c202.formybaby.sleep.service;

import com.ssafy.c202.formybaby.sleep.Dto.response.SleepCntResponse;
import com.ssafy.c202.formybaby.sleep.Dto.response.SleepTimeResponse;
import com.ssafy.c202.formybaby.sleep.entity.Sleep;
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

    @Override
    public List<SleepCntResponse> getWakeUpWeekList(Long babyId, String endDate) {
        try {
            // endDate 문자열을 Timestamp 객체로 변환
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
            Timestamp endDate2 = new Timestamp(dateFormat.parse(endDate).getTime());

            // 7일 전 날짜 계산
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(endDate2.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, -7);

            // startDate 생성
            Timestamp startDate = new Timestamp(calendar.getTimeInMillis());

            log.info("babyId :" + babyId);
            log.info("startDate :" + startDate);
            log.info("endDate2 :" + endDate2);

            List<Sleep> sleepList = sleepRepository.findByWeekSleepCnt(babyId, startDate, endDate2);

            log.info("sleepList :" + sleepList);

            List<SleepCntResponse> sleepCntResponseList = new ArrayList<>();

            for (Sleep sleep : sleepList) {
                // Sleep createdAt과 sleepCnt 추출하여 SleepCntResponse 객체 생성
                Timestamp createdAt = sleep.getCreatedAt();
                int sleepCnt = sleep.getSleepCnt();
                SleepCntResponse response = new SleepCntResponse(createdAt, sleepCnt);

                // 리스트에 추가
                sleepCntResponseList.add(response);
            }

            log.info("sleepCntResponseList :" + sleepCntResponseList);

            return sleepCntResponseList;
        } catch (ParseException e) {
            log.error("Error parsing date: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<SleepTimeResponse> getSleepTimeWeekList(String endDate) {
        return null;
    }
}
