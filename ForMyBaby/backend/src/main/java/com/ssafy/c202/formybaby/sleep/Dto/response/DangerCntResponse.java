package com.ssafy.c202.formybaby.sleep.Dto.response;

import java.sql.Timestamp;

public record DangerCntResponse(
    Timestamp createdAt,
    int dangerCnt
) {}