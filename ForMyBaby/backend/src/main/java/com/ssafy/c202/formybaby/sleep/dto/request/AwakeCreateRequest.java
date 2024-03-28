package com.ssafy.c202.formybaby.sleep.dto.request;

import com.ssafy.c202.formybaby.global.jpaEnum.SleepCategory;

import java.sql.Timestamp;

public record AwakeCreateRequest(
    Timestamp createdAt

) {}
