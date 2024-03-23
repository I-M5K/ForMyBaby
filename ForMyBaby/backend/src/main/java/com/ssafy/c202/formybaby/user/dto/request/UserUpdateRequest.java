package com.ssafy.c202.formybaby.user.dto.request;


public record UserUpdateRequest(
        Long userId,
        String name,
        String role

) {}
