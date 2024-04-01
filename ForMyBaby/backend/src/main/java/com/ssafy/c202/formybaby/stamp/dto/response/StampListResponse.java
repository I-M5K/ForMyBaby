package com.ssafy.c202.formybaby.stamp.dto.response;

import java.sql.Timestamp;

public record StampListResponse(
        Long stampId,
        Long babyId,
        Long step,
        String stampImg,
        String memo,
        Timestamp createdAt

) {
}

