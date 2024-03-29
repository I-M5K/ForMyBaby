package com.ssafy.c202.formybaby.sleep.dto.request;

import java.sql.Timestamp;

public record SleepOnCreateRequest(
        Timestamp createdAt
) {
}
