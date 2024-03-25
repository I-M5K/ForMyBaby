package com.ssafy.c202.formybaby.stamp.service;

import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse;
import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.baby.repository.BabyRepository;
import com.ssafy.c202.formybaby.stamp.dto.request.StampCreateRequest;
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

    @Override
    public void createStamp(StampCreateRequest stampCreateRequest) {
        log.info("stampCreateRequest : " + stampCreateRequest);
        Stamp stamp = new Stamp();

        Baby baby = new Baby();

        // 로그인한 유저의 현재 babyId 가져오는 로직 필요!
        // 이걸 통해서 베이비 조회 후 그 값을 넣어줘야한다.
        //Optional<BabyReadResponse> babyReadResponse = babyRepository.findBabyByBabyId(1L);
        //Long babyId = babyReadResponse.get().babyId();

        Long babyId = babyRepository.findBabyByBabyId(1L).get().babyId();
        baby.setBabyId(babyId);

        stamp.setBaby(baby);

        stamp.setStep(stampCreateRequest.step());
        stamp.setStampImg(stampCreateRequest.stampImg().toString());
        stamp.setMemo(stampCreateRequest.memo());
        stamp.setCreatedAt((stampCreateRequest.createdAt()));

        log.info("stamp : " + stamp);

        stampRepository.save(stamp);
    }

    @Override
    public List<StampListResponse> stampListResponse(Long babyId) {
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

    @Override
    public void updateStamp(Long stampId, StampUpdateRequest stampUpdateRequest) {
        Stamp stamp = stampRepository.findByStampId(stampId);
        if(stamp != null){
            log.info("stamp update success");
            stamp.setStampImg(stampUpdateRequest.stampImg().toString());
            stamp.setMemo(stampUpdateRequest.memo());
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
}
