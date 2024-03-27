package com.ssafy.c202.formybaby.user.dto.request;

import com.ssafy.c202.formybaby.global.jpaEnum.BabyGender;
import com.ssafy.c202.formybaby.global.jpaEnum.DangerType;

import java.sql.Date;
import java.util.List;

public record BabyCreateRequest (
        String babyName,
        Date birthDate,
        BabyGender babyGender,
        List<String> profileImg,
        String familyCode
) {}
