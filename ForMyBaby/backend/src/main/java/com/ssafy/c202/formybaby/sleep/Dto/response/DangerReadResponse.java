package com.ssafy.c202.formybaby.sleep.Dto.response;

import java.sql.Timestamp;

public record DangerReadResponse(
        Timestamp createdAt,
        String dangerType
) {
}
