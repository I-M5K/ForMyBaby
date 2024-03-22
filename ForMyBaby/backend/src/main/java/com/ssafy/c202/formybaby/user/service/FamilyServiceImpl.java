package com.ssafy.c202.formybaby.user.service;

import com.ssafy.c202.formybaby.user.dto.request.FamilyCodeUpdateRequest;
import com.ssafy.c202.formybaby.user.entity.Family;
import com.ssafy.c202.formybaby.user.mapper.FamilyMapper;
import com.ssafy.c202.formybaby.user.repository.FamilyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FamilyServiceImpl implements FamilyService{

    private final FamilyRepository familyRepository;

    private final FamilyMapper familyMapper;
    @Override
    public int checkFamilyCode(FamilyCodeUpdateRequest familyCodeUpdateRequest) {
        Family family;
        return 0;
    }
}
