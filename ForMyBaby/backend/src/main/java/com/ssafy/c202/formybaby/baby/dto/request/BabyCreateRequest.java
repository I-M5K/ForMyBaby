package com.ssafy.c202.formybaby.baby.dto.request;

import com.ssafy.c202.formybaby.global.jpaEnum.BabyGender;
import com.ssafy.c202.formybaby.global.jpaEnum.Role;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;

public record BabyCreateRequest(
        Long userId,
        String babyName,
        String birthDate,
        BabyGender babyGender,

        //S3 설정 후 변경
        //List<MultipartFile> files
        MultipartFile files,
        //String profileImg,
        Role role

)
{ }

