package com.ssafy.c202.formybaby.sleep.dto.response;

import com.ssafy.c202.formybaby.sleep.entity.Danger;
import com.ssafy.c202.formybaby.sleep.entity.Sleep;

public record SleepAllList(
        Sleep sleep,
        Danger danger
) {
}
