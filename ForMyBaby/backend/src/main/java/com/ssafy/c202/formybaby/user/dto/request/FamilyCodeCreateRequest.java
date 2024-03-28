package com.ssafy.c202.formybaby.user.dto.request;

import com.ssafy.c202.formybaby.global.jpaEnum.Role;

public record FamilyCodeCreateRequest(
        Long userId,
        String familyCode,
        Role role
) {}
