package com.ssafy.c202.formybaby.stamp.service;

import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.stamp.dto.request.StampCreateRequest;
import com.ssafy.c202.formybaby.stamp.entity.Stamp;
import com.ssafy.c202.formybaby.stamp.repository.StampRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
@Slf4j
public class StampServiceImpl implements StampService{

    private final StampRepository stampRepository;

    @Override
    public void createStamp(StampCreateRequest stampCreateRequest) {
        log.info("stampCreateRequest : " + stampCreateRequest);
        Stamp stamp = new Stamp();
        Baby baby = new Baby();
        baby.setBabyId(1L);
        stamp.setBaby(baby);
        stamp.setStep(stampCreateRequest.step());
        stamp.setMemo(stampCreateRequest.memo());
        stamp.setCreatedAt((stampCreateRequest.createdAt()));
        log.info("stamp : " + stamp);
        stampRepository.save(stamp);
    }
}
