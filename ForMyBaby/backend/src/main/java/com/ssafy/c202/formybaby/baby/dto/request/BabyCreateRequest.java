package com.ssafy.c202.formybaby.baby.dto.request;

import com.ssafy.c202.formybaby.global.jpaEnum.BabyGender;

import java.sql.Date;

public record BabyCreateRequest(
        Long userId,
        String babyName,
        Date birthDate,
        BabyGender babyGender,

        //S3 설정 후 변경
        //List<MultipartFile> files

        String profileImg,
        String role,
        String familyCode
)
{ }
