package com.ssafy.c202.formybaby.baby.dto.request;

import java.util.List;

public record BabyCreateListRequest(
        List<BabyCreateRequest> list
) { }
