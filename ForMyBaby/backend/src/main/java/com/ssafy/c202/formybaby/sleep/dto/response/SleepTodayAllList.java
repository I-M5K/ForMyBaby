package com.ssafy.c202.formybaby.sleep.dto.response;

import com.ssafy.c202.formybaby.sleep.entity.Danger;
import com.ssafy.c202.formybaby.sleep.entity.Sleep;

import java.sql.Timestamp;

public record SleepTodayAllList(
        int sleepTime,
        int sleepCnt,
        int dangerCnt
) {
}
