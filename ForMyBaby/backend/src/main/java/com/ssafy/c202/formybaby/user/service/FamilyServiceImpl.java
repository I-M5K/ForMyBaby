package com.ssafy.c202.formybaby.user.service;

import com.ssafy.c202.formybaby.baby.dto.request.BabyCreateRequest;
import com.ssafy.c202.formybaby.baby.service.BabyService;
import com.ssafy.c202.formybaby.global.jpaEnum.Role;
import com.ssafy.c202.formybaby.global.redis.RedisService;
import com.ssafy.c202.formybaby.user.dto.request.FamilyCodeUpdateRequest;
import com.ssafy.c202.formybaby.user.entity.Family;
import com.ssafy.c202.formybaby.user.entity.User;
import com.ssafy.c202.formybaby.user.mapper.FamilyMapper;
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

    private final RedisService redisService;

    private final UserService userService;

    @Override
    public int checkFamily(@RequestHeader(name = "Authorization") String token, FamilyCodeUpdateRequest familyCodeUpdateRequest) {

        List<Family> checkfamily = familyRepository.findByFamilyId(Long.valueOf(familyCodeUpdateRequest.familyCode()));

        User user = userService.findByUserId(Long.valueOf(redisService.getUserIdByToken(token)));

        if(checkfamily != null){
            for (Family existingFamily : checkfamily){
                Family family = new Family();
                family.setUser(user);
                family.setBaby(existingFamily.getBaby());
                family.setFamilyCode(familyCodeUpdateRequest.familyCode());
                family.setRole(Role.valueOf(familyCodeUpdateRequest.role()));
                familyRepository.save(family);
            }
            return 1;
        }
        else{
            return 0;
        }
    }
}
