package com.ssafy.c202.formybaby.stamp.dto.request;

import java.util.List;

public record StampUpdateRequest(
        List <String> stampImg,
        String memo
) {}
