package com.ssafy.c202.formybaby.stamp.dto.request;

import java.sql.Timestamp;
import java.util.List;

public record StampCreateRequest(
        Long step,
        List<String> stampImg,
        String memo,
        String createdAt
){}

