package com.ssafy.c202.formybaby.user.dto.request;

import com.ssafy.c202.formybaby.global.jpaEnum.BabyGender;

import java.sql.Date;
import java.sql.Timestamp;

public record BabyUpdateRequest(
        String babyName,
        Date birthDate,
        BabyGender babyGender
) {}
