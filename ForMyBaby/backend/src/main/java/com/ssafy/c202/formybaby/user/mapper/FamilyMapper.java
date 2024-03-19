package com.ssafy.c202.formybaby.user.mapper;

import com.ssafy.c202.formybaby.baby.dto.request.BabyCreateRequest;
import com.ssafy.c202.formybaby.user.entity.Family;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FamilyMapper {
    Family initFamilyEntity(Long userId, String familyCode);
    Family updateFamilyChild(BabyCreateRequest babyCreateRequest);
}
