package com.ssafy.c202.formybaby.user.mapper;

import com.ssafy.c202.formybaby.baby.dto.request.BabyCreateRequest;
import com.ssafy.c202.formybaby.baby.dto.request.BabyUpdateRequest;
import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.user.entity.Family;
import com.ssafy.c202.formybaby.user.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface FamilyMapper {
    Family initFamilyEntity(User user, Baby baby, BabyCreateRequest babyCreateRequest, String familyCode, int familyRank);
    Family copyFamily(Family family);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Family updateFamilyChild(BabyCreateRequest babyCreateRequest, @MappingTarget Family family);
}
