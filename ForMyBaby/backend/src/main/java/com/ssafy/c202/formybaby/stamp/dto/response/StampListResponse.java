

package com.ssafy.c202.formybaby.stamp.dto.response;

public record StampListResponse(
        Long stampId,
        Long babyId,
        Long step,
        String stampImg,
        String memo,
        String createdAt

) {
}

