package com.ssafy.c202.formybaby.stopmotion.service;

public interface StopmotionService {
    String findMotionUrlByBabyId(Long babyId);

    int countImagesByBabyId(Long babyId);
}


