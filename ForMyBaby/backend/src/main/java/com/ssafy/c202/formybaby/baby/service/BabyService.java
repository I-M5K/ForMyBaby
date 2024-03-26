package com.ssafy.c202.formybaby.baby.service;

import com.ssafy.c202.formybaby.baby.dto.request.BabyCreateListRequest;
import com.ssafy.c202.formybaby.baby.dto.request.BabyCreateRequest;
import com.ssafy.c202.formybaby.baby.dto.request.BabyUpdateRequest;
import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse;
import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse2;
import com.ssafy.c202.formybaby.user.dto.response.FamilyReadResponse;

import java.util.List;

public interface BabyService {
    FamilyReadResponse createNewBaby2(String token, BabyCreateRequest babyCreateRequest);

    void addBaby(BabyCreateRequest babyCreateRequest);

    void createNewBaby(BabyCreateListRequest babyCreateListRequest);

    BabyReadResponse updateBaby(BabyUpdateRequest babyUpdateRequest);
    BabyReadResponse babyDetail(Long babyId);
    List<BabyReadResponse> babyList(Long userId);
    List<BabyReadResponse> babyList(String familyCode);
    List<BabyReadResponse2> babyList2(String familyCode);
    void deleteBaby(Long babyId);
}
