package com.ssafy.c202.formybaby.user.dto.response;


import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse;

import java.util.List;

public record FamilyReadResponse(
        List<BabyReadResponse> babyReadResponseList
) {}
