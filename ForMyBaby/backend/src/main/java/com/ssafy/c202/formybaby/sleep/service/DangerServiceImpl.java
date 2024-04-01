package com.ssafy.c202.formybaby.sleep.service;

import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.baby.repository.BabyRepository;
import com.ssafy.c202.formybaby.global.redis.RedisService;
import com.ssafy.c202.formybaby.sleep.dto.response.DangerCntResponse;
import com.ssafy.c202.formybaby.sleep.dto.request.DangerCreateRequest;
import com.ssafy.c202.formybaby.sleep.dto.response.DangerReadResponse;
import com.ssafy.c202.formybaby.sleep.entity.Danger;
import com.ssafy.c202.formybaby.sleep.repository.DangerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DangerServiceImpl implements DangerService {

    private final DangerRepository dangerRepository;

    private final RedisService redisService;

    private final BabyRepository babyRepository;

    @Override
    public List<DangerReadResponse> selectWeekDangerList(Long babyId, String endDate) {
        // endDate 문자열에서 연, 월, 일 추출
        int year = Integer.parseInt(endDate.substring(0, 4));
        int month = Integer.parseInt(endDate.substring(5, 7));
        int day = Integer.parseInt(endDate.substring(8, 10));

        // endDate를 Calendar 객체로 변환
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day); // month는 0부터 시작하므로 -1

        // endDate의 시간을 00:00:00.000으로 설정
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Timestamp endDate2 = Timestamp.valueOf(endDate);

        // 7일 전 날짜 계산
        calendar.add(Calendar.DAY_OF_MONTH, -7);

        // startDate 생성
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        Timestamp startDate = Timestamp.valueOf(dateFormat.format(calendar.getTime()));

        log.info("startDate : " + startDate);
        log.info("endDate2 : " + endDate2);

        // Danger 엔티티를 조회
        List<Danger> dangerList = dangerRepository.findByBaby_BabyIdAndCreatedAt(babyId, startDate, endDate2);

        List<DangerReadResponse> dangerReadResponseList = new ArrayList<>();

        for (Danger danger : dangerList) {
            // Danger에서 createdAt과 dangerType 추출하여 DangerReadResponse 객체 생성
            Timestamp createdAt = danger.getCreatedAt();
            String dangerType = danger.getDangerType().toString();
            DangerReadResponse response = new DangerReadResponse(createdAt, dangerType);

            // 리스트에 추가
            dangerReadResponseList.add(response);
        }

        return dangerReadResponseList;
    }

    @Override
    public List<DangerCntResponse> selectWeekDangerCntList(int dangerCnt, String endDate) {
        // endDate 문자열에서 연, 월, 일 추출
        int year = Integer.parseInt(endDate.substring(0, 4));
        int month = Integer.parseInt(endDate.substring(5, 7));
        int day = Integer.parseInt(endDate.substring(8, 10));

        // endDate를 Calendar 객체로 변환
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day); // month는 0부터 시작하므로 -1

        // endDate의 시간을 00:00:00.000으로 설정
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Timestamp endDate2 = Timestamp.valueOf(endDate);

        // 7일 전 날짜 계산
        calendar.add(Calendar.DAY_OF_MONTH, -7);

        // startDate 생성
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        Timestamp startDate = Timestamp.valueOf(dateFormat.format(calendar.getTime()));

        // Danger 엔티티를 조회
        List<Danger> dangerList = dangerRepository.findByDangerCntAndCreatedAt(dangerCnt, startDate, endDate2);

        List<DangerCntResponse> dangerCntResponseList = new ArrayList<>();

        for (Danger danger : dangerList) {
            // Danger에서 createdAt과 dangerType 추출하여 DangerReadResponse 객체 생성
            Timestamp createdAt = danger.getCreatedAt();
            int dangerCnt1 = danger.getDangerCnt();
            DangerCntResponse response = new DangerCntResponse(createdAt, dangerCnt1);

            // 리스트에 추가
            dangerCntResponseList.add(response);
        }

        return dangerCntResponseList;
    }

    @Override
    public void createDanger(String code, DangerCreateRequest dangerCreateRequest) {
        Long babyId = Long.valueOf(redisService.getBabyIdByToken(redisService.getUserIdByToken(code)));
        Baby baby = babyRepository.findByBabyId(babyId);

        log.info("dangerCreateRequest {} : " +dangerCreateRequest);

        // Danger 엔티티를 조회
        List<Danger> dangerList = dangerRepository.findAllByBaby_BabyIdOrderByCreatedAtDesc(baby.getBabyId());

        if (!dangerList.isEmpty()) {
            // dangerList가 비어있지 않은 경우에만 첫 번째 Danger 엔티티를 가져옴
            Danger firstDanger = dangerList.get(0);
            Danger danger = new Danger();
            // Danger 엔티티를 수정하여 저장
            danger.setDangerCnt(firstDanger.getDangerCnt() + 1);
            danger.setCreatedAt(Timestamp.valueOf(dangerCreateRequest.createdAt()));
            danger.setDangerType(dangerCreateRequest.dangerType());
            danger.setBaby(baby);
            dangerRepository.save(danger);
        } else {
            // dangerList가 비어있는 경우 새로운 Danger 엔티티를 생성하여 저장
            Danger newDanger = new Danger();
            newDanger.setDangerCnt(1);
            newDanger.setCreatedAt(Timestamp.valueOf(dangerCreateRequest.createdAt()));
            newDanger.setDangerType(dangerCreateRequest.dangerType());
            newDanger.setBaby(baby);
            dangerRepository.save(newDanger);
        }
    }
}
