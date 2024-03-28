package com.ssafy.c202.formybaby.sleep.service;

import com.ssafy.c202.formybaby.sleep.dto.response.SleepAllList;
import com.ssafy.c202.formybaby.sleep.dto.response.SleepTodayAllList;
import com.ssafy.c202.formybaby.sleep.dto.response.SleepWeekAllList;

import java.sql.Timestamp;
import java.util.List;

public interface SleepService {
    SleepWeekAllList getWeekAllList(String token, Timestamp createdAt);
    SleepTodayAllList getTodayAllList(String token);
    void getSleepOnTime(String token,Timestamp createdAt);
    void getAwakeTimeList(String token, Timestamp endAt);

}
