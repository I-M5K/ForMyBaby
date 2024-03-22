package com.ssafy.c202.formybaby.user.dto.response;


public record UserReadResponse(
        Long userId,
        String name,
        String profileImg
) {}
