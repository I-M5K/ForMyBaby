package com.ssafy.c202.formybaby.user.service;

import com.ssafy.c202.formybaby.baby.dto.request.BabyCreateRequest;
import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse;
import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse2;
import com.ssafy.c202.formybaby.user.dto.request.FamilyCodeCreateRequest;
import com.ssafy.c202.formybaby.user.dto.request.FamilyCodeUpdateRequest;
import com.ssafy.c202.formybaby.user.dto.response.FamilyReadResponse;
import com.ssafy.c202.formybaby.user.entity.Family;

import java.util.List;

public interface FamilyService {
    List<Family> familyList(Long userId);
    List<BabyReadResponse2> checkFamily(String token, FamilyCodeUpdateRequest familyCodeUpdateRequest);
    List<BabyReadResponse2> checkFamily2(String token, FamilyCodeUpdateRequest familyCodeUpdateRequest);
    List<BabyReadResponse2> joinFamilyWithShareCode(String token, FamilyCodeCreateRequest familyCodeCreateRequest);

    List<BabyReadResponse2> joinFamilyWithShareCode2(String token, Long userId, String role, String familyCode);
}
