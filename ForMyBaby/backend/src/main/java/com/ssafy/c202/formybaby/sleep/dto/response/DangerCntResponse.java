package com.ssafy.c202.formybaby.sleep.dto.response;

import java.sql.Timestamp;

public record DangerCntResponse(
    Timestamp createdAt,
    int dangerCnt
) {}
