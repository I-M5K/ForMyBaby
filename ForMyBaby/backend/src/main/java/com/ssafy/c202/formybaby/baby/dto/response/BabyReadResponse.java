package com.ssafy.c202.formybaby.baby.dto.response;

import com.ssafy.c202.formybaby.global.jpaEnum.BabyGender;
import com.ssafy.c202.formybaby.global.jpaEnum.Role;

import java.time.LocalDate;

public record BabyReadResponse(
        Long babyId,
        String babyName,
        LocalDate birthDate,
        BabyGender babyGender,
        String profileImg,
        String familyCode,
        Role role
){}
