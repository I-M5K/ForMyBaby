package com.ssafy.c202.formybaby.baby.dto.request;

import com.ssafy.c202.formybaby.global.jpaEnum.BabyGender;

public record BabyUpdateRequest (
        String babyId,
        String babyName,
        String birthDate,
        String profileImg,
        BabyGender babyGender
)
{

}
