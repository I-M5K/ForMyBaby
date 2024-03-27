package com.ssafy.c202.formybaby.stamp.service;

import com.ssafy.c202.formybaby.stamp.dto.request.StampCreateRequest;
import com.ssafy.c202.formybaby.stamp.dto.request.StampUpdateRequest;
import com.ssafy.c202.formybaby.stamp.dto.response.StampListResponse;

import java.util.List;

public interface StampService {
        void createStamp(StampCreateRequest stampCreateRequest);

        List<StampListResponse> stampListResponse(Long babyId);

        StampListResponse detailStamp(Long stampId);

        void updateStamp(Long stampId,StampUpdateRequest stampUpdateRequest);

        void deleteStamp(Long stampId);

}
