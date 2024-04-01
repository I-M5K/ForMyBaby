package com.ssafy.c202.formybaby.sleep.service;


import com.ssafy.c202.formybaby.global.jpaEnum.DangerType;
import com.ssafy.c202.formybaby.sleep.dto.response.DangerCntResponse;
import com.ssafy.c202.formybaby.sleep.dto.request.DangerCreateRequest;
import com.ssafy.c202.formybaby.sleep.dto.response.DangerReadResponse;
import com.ssafy.c202.formybaby.sleep.entity.Danger;

import java.util.List;

public interface DangerService {
    List<DangerReadResponse> selectWeekDangerList(Long babyId);
    List<DangerCntResponse> selectWeekDangerCntList(int cnt);
    void createDanger (String code, DangerType dangerType, Long babyId);
}
