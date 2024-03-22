package com.ssafy.c202.formybaby.stamp.dto.request;

import java.sql.Timestamp;

public record StampCreateRequest(
        Long step,
        String memo,
        String createdAt
){}

