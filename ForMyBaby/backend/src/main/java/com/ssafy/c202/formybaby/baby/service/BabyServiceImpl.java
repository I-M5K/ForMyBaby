package com.ssafy.c202.formybaby.baby.service;

import com.ssafy.c202.formybaby.baby.dto.request.BabyCreateRequest;
import com.ssafy.c202.formybaby.baby.dto.request.BabyUpdateRequest;
import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse;
import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.baby.mapper.BabyMapper;
import com.ssafy.c202.formybaby.baby.repository.BabyRepository;
import com.ssafy.c202.formybaby.user.entity.Family;
import com.ssafy.c202.formybaby.user.mapper.FamilyMapper;
import com.ssafy.c202.formybaby.user.repository.FamilyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BabyServiceImpl implements BabyService{

    private final BabyRepository babyRepository;
    private final FamilyRepository familyRepository;
    private final BabyMapper babyMapper;
    private final FamilyMapper familyMapper;
    @Override
    public void createBaby(BabyCreateRequest babyCreateRequest) {
        Baby baby = babyMapper.toBabyEntity(babyCreateRequest);
        babyRepository.save(baby);

        Family family = familyMapper.updateFamilyChild(babyCreateRequest);
        familyRepository.save(family);
    }

    @Override
    public BabyReadResponse updateBaby(BabyUpdateRequest babyUpdateRequest) {
        Baby oldBaby = babyMapper.toBabyEntity(babyUpdateRequest);
        babyMapper.updateBabyFromRequest(babyUpdateRequest, oldBaby);

        return babyRepository.findBabyByBabyId(oldBaby.getBabyId())
                .orElseThrow(NullPointerException::new);
    }

    @Override
    public BabyReadResponse babyDetail(Long babyId) {
        return babyRepository.findBabyByBabyId(babyId).orElseThrow(NullPointerException::new);
    }

    @Override
    public List<BabyReadResponse> babyList(Long userId) {
        String familyCode = familyRepository.findFamilyCodeByUserId(userId);
        return babyRepository.findBabiesByUserId(userId);
    }

    @Override
    public void deleteBaby(Long babyId) {
        babyRepository.deleteByBabyId(babyId);
    }
}
