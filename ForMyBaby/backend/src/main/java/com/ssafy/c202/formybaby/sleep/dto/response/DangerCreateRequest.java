package com.ssafy.c202.formybaby.sleep.dto.response;

import com.ssafy.c202.formybaby.global.jpaEnum.DangerType;

import java.sql.Timestamp;

public record DangerCreateRequest(
        Timestamp createdAt,
        DangerType dangerType
) {
}
