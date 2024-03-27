package com.ssafy.c202.formybaby.baby.service;

import com.ssafy.c202.formybaby.baby.dto.request.BabyCreateListRequest;
import com.ssafy.c202.formybaby.baby.dto.request.BabyCreateRequest;
import com.ssafy.c202.formybaby.baby.dto.request.BabyUpdateRequest;
import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse;
import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse2;
import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.baby.mapper.BabyMapper;
import com.ssafy.c202.formybaby.baby.repository.BabyRepository;
import com.ssafy.c202.formybaby.fcm.service.FCMService;
import com.ssafy.c202.formybaby.global.redis.RedisService;
import com.ssafy.c202.formybaby.user.dto.response.FamilyReadResponse;
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
    private final RedisService redisService;
    @Override
    public void addBaby(BabyCreateRequest babyCreateRequest) {
        User user = userRepository.findByUserId(babyCreateRequest.userId());
        Baby baby = babyMapper.toBabyEntity(babyCreateRequest);
        String familyCode = familyRepository.findFamilyCodeByUserId(babyCreateRequest.userId());
        babyRepository.save(baby);
        Family family = familyMapper.initFamilyEntity(user, baby, babyCreateRequest, familyCode);
        familyRepository.save(family);
    }

    public void createNewBaby(List<BabyCreateRequest> babyCreateRequestList) {
        User user = userRepository.findByUserId(babyCreateRequestList.get(0).userId());
        String familyCode = RandomStringUtils.randomAlphanumeric(6);
        for(BabyCreateRequest babyCreateRequest : babyCreateRequestList){
            Baby baby = babyMapper.toBabyEntity(babyCreateRequest);
            babyRepository.save(baby);

//        fcmService.sendTest("fGEU-9IwnPvJFXs8VcFrHe:APA91bGmQ0bqr_Hxut3dxXPA3qOkpuS3u0ZNnwAR0Mc6YmDWFMsw4WgN8Ncp1VSpdXHz-OYKijYEUK0MHKTRrr_je5EzL7KKDuBjuoBoclMKsWVoSqmIExHLl1v2VqdG2Fb_dA7f29BG");

            Family family = familyMapper.initFamilyEntity(user, baby, babyCreateRequest, familyCode);
            familyRepository.save(family);
        }
    }
    public FamilyReadResponse createNewBaby2(String token, BabyCreateRequest babyCreateRequest) {
        User user = userRepository.findByUserId(babyCreateRequest.userId());
        log.info("User : " + user);
        Baby baby = babyMapper.toBabyEntity(babyCreateRequest);
        log.info("Baby : " + baby);
        String familyCode = RandomStringUtils.randomAlphanumeric(6);
        log.info("familyCode : " + familyCode);
        babyRepository.save(baby);
        Family family = familyMapper.initFamilyEntity(user, baby, babyCreateRequest, familyCode);
        familyRepository.save(family);

        // 현재 db에서 baby목록 가져와서
        List<BabyReadResponse> babyReadResponseList = babyRepository.findBabiesByFamilyCode(familyCode);
        for(BabyReadResponse babyReadResponse : babyReadResponseList){
            if(baby.getBabyId() == null || baby.getBabyId()<= babyReadResponse.babyId())
            baby.setBabyId(babyReadResponseList.get(0).babyId());
        }
        log.info("babyReadResponseList :" + babyReadResponseList);

        String userId = redisService.getUserIdByToken(token);

        //새로운 아이 등록 시 아이 번호 레디스에 저장.
        redisService.saveBabyIdsByToken(userId, baby.getBabyId());

        FamilyReadResponse familyReadResponse = new FamilyReadResponse(familyCode);
        log.info("familyReadResponse : " + familyReadResponse);
        return familyReadResponse;
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
    public List<BabyReadResponse2> babyList2(String familyCode) {
        return babyRepository.findBabiesByFamilyCode2(familyCode);
    }

    @Override
    public void deleteBaby(Long babyId) {
        familyRepository.deleteFamiliesByBabyBabyId(babyId);
        babyRepository.deleteByBabyId(babyId);
    }
}
