package com.ssafy.c202.formybaby.sleep.service;

import com.ssafy.c202.formybaby.sleep.dto.response.SleepTodayAllList;
import com.ssafy.c202.formybaby.sleep.dto.response.SleepWeekAllList;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public interface SleepService {
    SleepWeekAllList getWeekAllList(String token, Timestamp endTime);
    SleepTodayAllList getTodayAllList(String token);
    void getSleepOnTime(String token, Long babyId);
    void getAwakeOnTime(String token, Long babyId);
}
