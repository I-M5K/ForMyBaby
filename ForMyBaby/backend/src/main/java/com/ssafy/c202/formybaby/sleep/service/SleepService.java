package com.ssafy.c202.formybaby.sleep.service;

import com.ssafy.c202.formybaby.sleep.dto.response.SleepTodayAllList;
import com.ssafy.c202.formybaby.sleep.dto.response.SleepWeekAllList;

import java.sql.Timestamp;
import java.util.List;

public interface SleepService {
    SleepWeekAllList getWeekAllList(String token);
    SleepTodayAllList getTodayAllList(String token);
    void getSleepOnTime(String token);
    void getAwakeTimeList(String token);
}
