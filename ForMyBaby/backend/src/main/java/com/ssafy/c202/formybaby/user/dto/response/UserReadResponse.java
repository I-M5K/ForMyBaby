package com.ssafy.c202.formybaby.user.dto.response;


import com.ssafy.c202.formybaby.global.jpaEnum.Role;

public record UserReadResponse(
        Long userId,
        String name,
        String profileImg,
        Role role
) {}
