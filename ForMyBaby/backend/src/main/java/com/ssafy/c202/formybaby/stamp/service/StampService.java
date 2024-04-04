package com.ssafy.c202.formybaby.stamp.service;

import com.ssafy.c202.formybaby.stamp.dto.request.StampCreateAIRequest;
import com.ssafy.c202.formybaby.stamp.dto.request.StampCreateRequest;
import com.ssafy.c202.formybaby.stamp.dto.request.StampUpdateAIRequest;
import com.ssafy.c202.formybaby.stamp.dto.request.StampUpdateRequest;
import com.ssafy.c202.formybaby.stamp.dto.response.StampListResponse;

import java.util.List;

public interface StampService {
        void createStamp(String token, StampCreateRequest stampCreateRequest);

        void createStampAI(String token, StampCreateAIRequest stampCreateAIRequest);

        List<StampListResponse> stampListResponse(String token);

        StampListResponse detailStamp(Long stampId);

        void updateStamp(Long stampId,StampUpdateRequest stampUpdateRequest);

        void updateAIStamp(Long stampId, StampUpdateAIRequest stampUpdateAIRequest);

        void deleteStamp(Long stampId);

}
