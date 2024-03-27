package com.ssafy.c202.formybaby.sleep.service;


import com.ssafy.c202.formybaby.sleep.dto.response.DangerCntResponse;
import com.ssafy.c202.formybaby.sleep.dto.response.DangerReadResponse;

import java.util.List;

public interface DangerService {
    List<DangerReadResponse> selectWeekDangerList(Long babyId, String endDate);

    List<DangerCntResponse> selectWeekDangerCntList(int cnt, String endDate);
}
