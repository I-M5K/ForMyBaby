package com.ssafy.c202.formybaby.baby.dto.response;

import com.ssafy.c202.formybaby.global.jpaEnum.BabyGender;
import com.ssafy.c202.formybaby.global.jpaEnum.Role;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record BabyReadResponse2(
        Long babyId,
        String babyName,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate birthDate,
        BabyGender babyGender,
        String profileImg,
        String familyCode,
        Role role
){
    public void setRole(Role role) {
    }
}
