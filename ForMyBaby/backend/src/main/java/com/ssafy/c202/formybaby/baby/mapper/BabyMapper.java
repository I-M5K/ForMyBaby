package com.ssafy.c202.formybaby.baby.mapper;

import com.ssafy.c202.formybaby.baby.dto.request.BabyCreateRequest;
import com.ssafy.c202.formybaby.baby.dto.request.BabyUpdateRequest;
import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse2;
import com.ssafy.c202.formybaby.baby.entity.Baby;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface BabyMapper {
    Baby toBabyEntity(BabyCreateRequest babyCreateRequest);
    Baby toBabyEntity(BabyReadResponse2 babyReadResponse2);
    Baby toBabyEntity(BabyUpdateRequest babyUpdateRequest);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBabyFromRequest(BabyUpdateRequest babyUpdateRequest, @MappingTarget Baby baby);
}
