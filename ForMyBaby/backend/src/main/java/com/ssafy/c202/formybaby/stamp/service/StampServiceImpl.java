package com.ssafy.c202.formybaby.stamp.service;

import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse;
import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.baby.repository.BabyRepository;
import com.ssafy.c202.formybaby.global.redis.RedisService;
import com.ssafy.c202.formybaby.global.s3.AwsS3Service;
import com.ssafy.c202.formybaby.stamp.dto.request.StampCreateAIRequest;
import com.ssafy.c202.formybaby.stamp.dto.request.StampCreateRequest;
import com.ssafy.c202.formybaby.stamp.dto.request.StampUpdateAIRequest;
import com.ssafy.c202.formybaby.stamp.dto.request.StampUpdateRequest;
import com.ssafy.c202.formybaby.stamp.dto.response.StampListResponse;
import com.ssafy.c202.formybaby.stamp.entity.Stamp;
import com.ssafy.c202.formybaby.stamp.repository.StampRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StampServiceImpl implements StampService{

    private final StampRepository stampRepository;
    private final BabyRepository babyRepository;
    private final RedisService redisService;
    private final AwsS3Service awsS3Service;

    @Override
    public void createStamp(String token, StampCreateRequest stampCreateRequest) {
        log.info("stampCreateRequest : " + stampCreateRequest);
        Timestamp timestamp = getCurrentTimestamp();

        Long babyId = Long.valueOf(redisService.getBabyIdByToken(redisService.getBabyIdByToken(token)));
        Baby baby = babyRepository.findByBabyId(babyId);

        Stamp stamp = new Stamp();
        stamp.setBaby(baby);
        stamp.setStep(stampCreateRequest.step());
        String imageUrl = awsS3Service.uploadFile(babyId,stampCreateRequest.stampImg(),timestamp,"stamp");
        stamp.setStampImg(imageUrl);
        stamp.setMemo(stampCreateRequest.memo());
        stamp.setCreatedAt(timestamp);

        log.info("stamp : " + stamp);

        stampRepository.save(stamp);
    }

    @Override
    public void createStampAI(String token, StampCreateAIRequest stampCreateAIRequest) {
        log.info("stampCreateAIRequest : " + stampCreateAIRequest);
        Long babyId = Long.valueOf(redisService.getBabyIdByToken(redisService.getBabyIdByToken(token)));
        Baby baby = babyRepository.findByBabyId(babyId);
        Timestamp timestamp = getCurrentTimestamp();

        Stamp stamp = new Stamp();
        stamp.setBaby(baby);
        stamp.setStep(stampCreateAIRequest.step());
        stamp.setStampImg(stampCreateAIRequest.stampUrl());
        stamp.setMemo(stampCreateAIRequest.memo());
        stamp.setCreatedAt(timestamp);

        log.info("stamp : " + stamp);

        stampRepository.save(stamp);
    }

    @Override
    public List<StampListResponse> stampListResponse(String token) {
        Long babyId = Long.valueOf(redisService.getBabyIdByToken(redisService.getBabyIdByToken(token)));
        List<Stamp> stampList = stampRepository.findByBaby_BabyId(babyId);
        List<StampListResponse> stampListResponseList = new ArrayList<>();
        for (Stamp stamp : stampList) {
            StampListResponse stampListResponse = new StampListResponse(
                    stamp.getStampId(),
                    stamp.getBaby().getBabyId(),
                    stamp.getStep(),
                    stamp.getStampImg(),
                    stamp.getMemo(),
                    stamp.getCreatedAt()
            );
            stampListResponseList.add(stampListResponse);
        }
        return stampListResponseList;
    }

    @Override
    public StampListResponse detailStamp(Long stampId) {
        Stamp stamp = stampRepository.findByStampId(stampId);
        StampListResponse stampListResponse = new StampListResponse(
                stamp.getStampId(),
                stamp.getBaby().getBabyId(),
                stamp.getStep(),
                stamp.getStampImg(),
                stamp.getMemo(),
                stamp.getCreatedAt()

        );
        return stampListResponse;
    }

    // 유저가 스탬프를 수정하는 경우
    @Override
    public void updateStamp(Long stampId, StampUpdateRequest stampUpdateRequest) {
        // 기존에 있는 스탬프 조회
        Stamp stamp = stampRepository.findByStampId(stampId);
        if(stamp != null){
            log.info("stamp update success");
            String imageUrl = awsS3Service.uploadFile(stamp.getBaby().getBabyId(), stampUpdateRequest.stampImg(),stamp.getCreatedAt(),"stamp");
            stamp.setStampImg(imageUrl);
            stamp.setMemo(stampUpdateRequest.memo());
            stampRepository.save(stamp);
        }
        else{
            log.info("stamp update fail");
        }
    }

    @Override
    public void updateAIStamp(Long stampId, StampUpdateAIRequest stampUpdateAIRequest) {
        Stamp stamp = stampRepository.findByStampId(stampId);
        if(stamp != null){
            log.info("stamp update success");
            stamp.setStampImg(stampUpdateAIRequest.stampUrl());
            stamp.setMemo(stampUpdateAIRequest.memo());
            stampRepository.save(stamp);
        }
        else{
            log.info("stamp update fail");
        }
    }

    @Override
    public void deleteStamp(Long stampId) {
        stampRepository.deleteById(stampId);
    }

    // 현재 시간을 Timestamp 객체로 가져오는 메서드
    public Timestamp getCurrentTimestamp() {
        // 현재 시간을 밀리초로 가져옴
        long currentTimeMillis = System.currentTimeMillis();
        // 밀리초를 Timestamp 객체로 변환하여 반환
        return new Timestamp(currentTimeMillis);
    }
}
