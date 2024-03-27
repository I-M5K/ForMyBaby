package com.ssafy.c202.formybaby.user.service;

import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse;
import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse2;
import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.baby.repository.BabyRepository;
import com.ssafy.c202.formybaby.baby.service.BabyService;
import com.ssafy.c202.formybaby.global.jpaEnum.Role;
import com.ssafy.c202.formybaby.global.redis.RedisService;
import com.ssafy.c202.formybaby.user.dto.request.FamilyCodeCreateRequest;
import com.ssafy.c202.formybaby.user.dto.request.FamilyCodeUpdateRequest;
import com.ssafy.c202.formybaby.user.dto.response.FamilyReadResponse;
import com.ssafy.c202.formybaby.user.entity.Family;
import com.ssafy.c202.formybaby.user.entity.User;
import com.ssafy.c202.formybaby.user.repository.FamilyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FamilyServiceImpl implements FamilyService{

    private final FamilyRepository familyRepository;

    private final BabyService babyService;

    private final RedisService redisService;

    @Override
    public List<Family> familyList(Long userId) {
        try{
            List<Family> familyList = familyRepository.findFamiliesByUserUserId(userId);
            return familyList;
        } catch (Exception e){
            log.error("Error occurred while fetching family list: " + e.getMessage());
            throw new RuntimeException("Failed to fetch family list");
        }
    }
    @Override
    public List<BabyReadResponse2> checkFamily(@RequestHeader(name = "Authorization") String token, FamilyCodeUpdateRequest familyCodeUpdateRequest) {
        List<BabyReadResponse2> babyReadResponse2List = babyService.babyList2(familyCodeUpdateRequest.familyCode());
        if(babyReadResponse2List != null){
            // 모든 BabyReadResponse2 객체의 role을 None으로 설정하고 데이터베이스에 저장
            for (BabyReadResponse2 baby : babyReadResponse2List) {
                Family family = familyRepository.findFamilyByBabyBabyId(baby.babyId());
                if(family != null){
                    family.setRole(Role.None);
                    familyRepository.save(family);
                }
            }
            return babyReadResponse2List;
        } else {
            return null;
        }
    }

    @Override
    public List<BabyReadResponse2> joinFamilyWithShareCode(String token, FamilyCodeCreateRequest familyCodeCreateRequest) {
        List<BabyReadResponse2> babyReadResponse2List = babyService.babyList2(familyCodeCreateRequest.familyCode());
        // 가족 공유 코드로 회원 가입 시 처음 아이번호를 레디스에 저장
        String userId = redisService.getUserIdByToken(token);
        redisService.saveBabyIdsByToken(userId , babyReadResponse2List.get(0).babyId());
        if(babyReadResponse2List != null){
            for (BabyReadResponse2 baby : babyReadResponse2List) {
                Family family = familyRepository.findFamilyByBabyBabyId(baby.babyId());
                if(family != null){
                    family.setRole(familyCodeCreateRequest.role());
                    familyRepository.save(family);
                }
            }
            return babyReadResponse2List;
        } else {
            return null;
        }
    }
}
