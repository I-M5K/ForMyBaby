package com.ssafy.c202.formybaby.baby.service;

import com.ssafy.c202.formybaby.baby.dto.request.BabyCreateListRequest;
import com.ssafy.c202.formybaby.baby.dto.request.BabyCreateRequest;
import com.ssafy.c202.formybaby.baby.dto.request.BabyUpdateRequest;
import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse;
import com.ssafy.c202.formybaby.baby.entity.Baby;

import java.util.List;

public interface BabyService {
    void addBaby(BabyCreateRequest babyCreateRequest);

    void createNewBaby(BabyCreateListRequest babyCreateListRequest);

    BabyReadResponse updateBaby(BabyUpdateRequest babyUpdateRequest);

    BabyReadResponse babyDetail(Long babyId);

    List<BabyReadResponse> babyList(Long userId);

    List<BabyReadResponse> babyList(String familyCode);

    void deleteBaby(Long babyId);
}
