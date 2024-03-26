package com.ssafy.c202.formybaby.sleep.service;


import com.ssafy.c202.formybaby.sleep.Dto.response.DangerCntResponse;
import com.ssafy.c202.formybaby.sleep.Dto.response.DangerReadResponse;

import java.sql.Timestamp;
import java.util.List;

public interface DangerService {
    List<DangerReadResponse> selectWeekDangerList(Long babyId, String endDate);

    List<DangerCntResponse> selectWeekDangerCntList(int cnt, String endDate);
}
