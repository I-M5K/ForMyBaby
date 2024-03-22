package com.ssafy.c202.formybaby.user.service;

import com.ssafy.c202.formybaby.baby.dto.request.BabyCreateRequest;
import com.ssafy.c202.formybaby.user.dto.request.FamilyCodeUpdateRequest;

public interface FamilyService {
    int checkFamily(String token,FamilyCodeUpdateRequest familyCodeUpdateRequest);
}
