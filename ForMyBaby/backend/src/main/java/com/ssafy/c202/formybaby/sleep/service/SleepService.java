package com.ssafy.c202.formybaby.sleep.service;

import com.ssafy.c202.formybaby.sleep.dto.response.SleepAllList;

import java.sql.Timestamp;
import java.util.List;

public interface SleepService {
    SleepAllList getTodayAllList(String token);
    void getSleepOnTime(String token,Timestamp createdAt);
    void getAwakeTimeList(String token, Timestamp endAt);

}
