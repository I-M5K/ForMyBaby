package com.ssafy.c202.formybaby.sleep.dto.request;

import com.ssafy.c202.formybaby.global.jpaEnum.DangerType;

import java.sql.Timestamp;

public record DangerCreateRequest(
        DangerType dangerType
) {
}
