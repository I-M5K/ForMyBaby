package com.ssafy.c202.formybaby.stopmotion.service;

import com.ssafy.c202.formybaby.stopmotion.entity.Stopmotion;
import com.ssafy.c202.formybaby.stopmotion.repository.StopmotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StopmotionServiceImpl implements StopmotionService {

    private final StopmotionRepository stopmotionRepository;

    @Override
    public String findMotionUrlByBabyId(Long babyId) {
        Optional<Stopmotion> stopmotionOptional = stopmotionRepository.findByBaby_BabyId(babyId);
        if (stopmotionOptional.isPresent()) {
            return stopmotionOptional.get().getMotionUrl();
        } else {
            throw new RuntimeException("해당 baby_id로 등록된 Stopmotion영상이 없습니다.");
        }
    }

    @Override
    public int countImagesByBabyId(Long babyId) {
        return stopmotionRepository.countByBaby_BabyId(babyId);
    }
}
