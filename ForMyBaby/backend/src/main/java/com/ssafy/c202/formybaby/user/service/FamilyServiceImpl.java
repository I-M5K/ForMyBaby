package com.ssafy.c202.formybaby.user.service;

import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse;
import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse2;
import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.baby.mapper.BabyMapper;
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
import com.ssafy.c202.formybaby.user.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final BabyService babyService;
    private final BabyRepository babyRepository;

    private final RedisService redisService;
    private final BabyMapper babyMapper;

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
    public List<BabyReadResponse2> checkFamily2(@RequestHeader(name = "Authorization") String token, FamilyCodeUpdateRequest familyCodeUpdateRequest) {
        System.out.println("id확인전");
        Long firstId = familyRepository.findFirstUserIdByFamilyCode(familyCodeUpdateRequest.familyCode()).get(0);
        log.info("firstId: " + firstId);
        Long userId = Long.parseLong(redisService.getUserIdByToken(token));
        log.info("userId: " + userId);
        System.out.println("firstId: " + firstId);
        log.info("firstId: " + firstId);
        if (firstId == null){
            return null;
        } else {
            List<Family> familyList = familyRepository.findAllByUserId(firstId);
            //List<Family> newList = new ArrayList<>();
            for (Family family: familyList){
                Family dto = new Family();
                dto.setFamilyRank(1);
                dto.setBaby(family.getBaby());
                dto.setRole(Role.None);
                dto.setFamilyCode(family.getFamilyCode());
                dto.setUser(userRepository.findByUserId(userId));
                familyRepository.save(dto);
            }
        }
        return babyService.babyList3(userId);
    }

    @Override
    public List<BabyReadResponse2> joinFamilyWithShareCode(String token, FamilyCodeCreateRequest familyCodeCreateRequest) {
        // 아이 리스트
        List<BabyReadResponse2> babyReadResponse2List = babyService.babyList2(familyCodeCreateRequest.familyCode());
        // Family 레코드들 모두 조회
        List<Family> familyList = familyRepository.findAllByFamilyCode(familyCodeCreateRequest.familyCode());
        List<Family> newList = new ArrayList<>();
        // 가족 공유 코드로 회원 가입 시 처음 아이번호를 레디스에 저장
        String userId = redisService.getUserIdByToken(token);
        redisService.saveBabyIdsByToken(userId , babyReadResponse2List.get(0).babyId());
        // 내 Family 레코드 조회
        List<Family> myFamilyList = familyRepository.findFamiliesByUserUserId(Long.valueOf(userId));
        if(!myFamilyList.isEmpty()){
            for (Family family : myFamilyList) {
                family.setRole(familyCodeCreateRequest.role());
                familyRepository.save(family);
            }
            familyRepository.saveAll(newList);
        } else {
            for(BabyReadResponse2 babyReadResponse2 : babyReadResponse2List){
            Family family = new Family();
            family.setFamilyCode(familyCodeCreateRequest.familyCode());
            family.setFamilyRank(1);
            family.setBaby(babyMapper.toBabyEntity(babyReadResponse2));
            family.setRole(familyCodeCreateRequest.role());
            family.setUser(userRepository.findByUserId(familyCodeCreateRequest.userId()));
            newList.add(family);
            }

        }
        return babyReadResponse2List;
    }

    @Override
    public List<BabyReadResponse2> joinFamilyWithShareCode2(String token, Long userId, String role, String familyCode) {
        // 아이 리스트
        //List<BabyReadResponse2> babyReadResponse2List = babyService.babyList2(familyCodeCreateRequest.familyCode());
        // Family 레코드들 모두 조회
        List<Family> familyList = familyRepository.findAllByUserId(userId);
        // 가족 공유 코드로 회원 가입 시 처음 아이번호를 레디스에 저장
        List<BabyReadResponse2> babyList = babyRepository.findBabiesByUserId2(userId);
        redisService.saveBabyIdsByToken(String.valueOf(userId), babyList.get(0).babyId());
        // 내 Family 레코드 조회
        //List<Family> myFamilyList = familyRepository.findFamiliesByUserUserId(Long.valueOf(userId));
        if (!familyList.isEmpty()) {
            for (Family family : familyList) {
                if (family.getRole() == Role.None) {
                    family.setRole(Role.valueOf(role));
                }

            }
        }
        return babyService.babyList3(userId);

    }
}
