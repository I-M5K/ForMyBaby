package com.ssafy.c202.formybaby.user.dto.request;


import com.ssafy.c202.formybaby.global.jpaEnum.Role;

import java.util.List;

public record UserUpdateRequest(
        Long userId,
        String name,
        List<String> profileImg,
        Role role

) {}
