package com.ssafy.c202.formybaby.baby.service;

import com.ssafy.c202.formybaby.baby.dto.request.BabyCreateRequest;
import com.ssafy.c202.formybaby.baby.dto.request.BabyUpdateRequest;
import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse;

import java.util.List;

public interface BabyService {
    void createBaby(BabyCreateRequest babyCreateRequest);

    BabyReadResponse updateBaby(BabyUpdateRequest babyUpdateRequest);

    BabyReadResponse babyDetail(Long babyId);

    List<BabyReadResponse> babyList(Long userId);

    void deleteBaby(Long babyId);
}
