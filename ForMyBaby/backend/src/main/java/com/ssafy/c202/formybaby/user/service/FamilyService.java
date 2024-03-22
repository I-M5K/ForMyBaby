package com.ssafy.c202.formybaby.user.service;

import com.ssafy.c202.formybaby.user.dto.request.FamilyCodeUpdateRequest;

public interface FamilyService {
    int checkFamilyCode(FamilyCodeUpdateRequest familyCodeUpdateRequest);
}
