package com.ssafy.c202.formybaby.stopmotion.service;

public interface StopMotionService {
    String findMotionUrlByBabyId(String token);
    int countImagesByBabyId(String token);
    void createStopMotionImage(String token, String imageUrl);
}

