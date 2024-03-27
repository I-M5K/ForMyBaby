package com.ssafy.c202.formybaby.sleep.service;

import com.ssafy.c202.formybaby.sleep.Dto.response.SleepCntResponse;
import com.ssafy.c202.formybaby.sleep.Dto.response.SleepTimeResponse;

import java.util.List;

public interface SleepService {
    List<SleepCntResponse> getWakeUpWeekList(Long babyId,String endDate);
    List<SleepTimeResponse> getSleepTimeWeekList(String endDate);

}
