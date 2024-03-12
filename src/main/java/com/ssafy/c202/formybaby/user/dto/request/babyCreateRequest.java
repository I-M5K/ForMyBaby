package com.ssafy.c202.formybaby.user.dto.request;

import com.ssafy.c202.formybaby.global.jpaEnum.BabyGender;
import com.ssafy.c202.formybaby.global.jpaEnum.DangerType;

import java.sql.Date;

public record babyCreateRequest (
        String babyName,
        Date birthDate,
        BabyGender babyGender,
        String familyCode
) {}
