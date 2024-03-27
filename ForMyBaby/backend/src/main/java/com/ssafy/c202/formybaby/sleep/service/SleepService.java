package com.ssafy.c202.formybaby.sleep.service;

import java.sql.Timestamp;
import java.util.List;

public interface SleepService {

    void getSleepOnTime(String token,Timestamp createdAt);
    void getAwakeTimeList(String token, Timestamp endAt);

}
