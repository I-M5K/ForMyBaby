package com.ssafy.c202.formybaby.baby.service;

import com.ssafy.c202.formybaby.baby.dto.request.BabyCreateRequest;
import com.ssafy.c202.formybaby.baby.dto.request.BabyUpdateRequest;
import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse;
import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.baby.mapper.BabyMapper;
import com.ssafy.c202.formybaby.baby.repository.BabyRepository;
import com.ssafy.c202.formybaby.fcm.service.FCMService;
import com.ssafy.c202.formybaby.user.entity.Family;
import com.ssafy.c202.formybaby.user.entity.User;
import com.ssafy.c202.formybaby.user.mapper.FamilyMapper;
import com.ssafy.c202.formybaby.user.repository.FamilyRepository;
import com.ssafy.c202.formybaby.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BabyServiceImpl implements BabyService{

    private final BabyRepository babyRepository;
    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;
    private final BabyMapper babyMapper;
    private final FamilyMapper familyMapper;
    private final FCMService fcmService;
    @Override
    public void createBaby(BabyCreateRequest babyCreateRequest) {
        User user = userRepository.findByUserId(babyCreateRequest.userId());
        Baby baby = babyMapper.toBabyEntity(babyCreateRequest);
        String familyCode = familyRepository.findFamilyCodeByUserId(babyCreateRequest.userId());
        babyRepository.save(baby);
        Family family = familyMapper.initFamilyEntity(user, baby, babyCreateRequest, familyCode);
        familyRepository.save(family);
    }

    public void createNewBaby(BabyCreateRequest babyCreateRequest) {
        User user = userRepository.findByUserId(babyCreateRequest.userId());
        Baby baby = babyMapper.toBabyEntity(babyCreateRequest);
        String familyCode = RandomStringUtils.randomAlphanumeric(6);
        babyRepository.save(baby);

        fcmService.sendTest("fnwM3OU363c7PthT1nsD5t:APA91bE-WIUrffTK4vj5e3r4M5KG-xA-MjMXPllTFIbK_pk-_8qna2337s6paCx_jE-2tHIkfWyt393FxKMrIPJ7q0q_nuMU9vhm02KHVMiZKvlsWpL7RPSnUrreDDe7pqBYCDl79Egi");

        Family family = familyMapper.initFamilyEntity(user, baby, babyCreateRequest, familyCode);
        familyRepository.save(family);
    }

    @Override
    public BabyReadResponse updateBaby(BabyUpdateRequest babyUpdateRequest) {
        Baby baby = babyMapper.toBabyEntity(babyUpdateRequest);
        babyMapper.updateBabyFromRequest(babyUpdateRequest, baby);

        babyRepository.save(baby);

        return babyRepository.findBabyByBabyId(baby.getBabyId())
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
    public List<BabyReadResponse> babyList(String familyCode) {
        return babyRepository.findBabiesByFamilyCode(familyCode);
    }

    @Override
    public void deleteBaby(Long babyId) {
        familyRepository.deleteFamiliesByBabyBabyId(babyId);
        babyRepository.deleteByBabyId(babyId);
    }
}
